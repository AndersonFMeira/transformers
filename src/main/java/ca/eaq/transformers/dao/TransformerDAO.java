package ca.eaq.transformers.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import ca.eaq.transformers.controller.TransformersException;
import ca.eaq.transformers.models.Transformer;
import ca.eaq.transformers.models.TransformerSpecs;

@Repository
public class TransformerDAO {

	private Map<String, TransformerSpecs> transformerTable;

	public TransformerDAO() {
		transformerTable = TransformersDaoLoader.loadTransformers();
	}

	public List<Transformer> findTransformers() {
		List<Transformer> transformers = new ArrayList<Transformer>();
		transformerTable.forEach((k, t) -> {
			Transformer transformer = new Transformer();
			transformer.setName(k);
			transformer.setSpecs(t);
			transformers.add(transformer);
		});
		return transformers;
	}

	public List<Transformer> findTransformersById(List<String> names) {
		names = names.stream().filter(n -> transformerTable.containsKey(n)).collect(Collectors.toList());
		List<Transformer> transformers = new ArrayList<Transformer>();
		names.forEach(n -> transformers.add(findTransformer(n)));
		return transformers;
	}

	public Transformer findTransformer(String name) {
		
		Transformer transformer = new Transformer();
		if (transformerTable.containsKey(name)) {
			transformer.setName(name);
			transformer.setSpecs(transformerTable.get(name));
		}
		return transformer;
	}

	public void persistTransformer(Transformer transformer) throws TransformersException {
		checkBeforePersist(transformer);
		transformerTable.putIfAbsent(transformer.getName(), transformer.getSpecs());
	}

	public void mergeTransformer(Transformer transformer) throws TransformersException {
		checkBeforeMerge(transformer);
		transformerTable.replace(transformer.getName(), transformer.getSpecs());
	}

	public void removeTransformer(String name) throws TransformersException {
		checkContent(name);
		transformerTable.remove(name);
	}

	/**
	 * Exceptions 
	 * @param transformer
	 * @throws TransformersException
	 */
	private void checkBeforePersist(Transformer transformer) throws TransformersException {
		checkInvalidTeam(transformer.getSpecs().getTeam());
		checkContentNullKey(transformer.getName());
		checkDuplicatedContentKey(transformer.getName());
	}
	
	private void checkBeforeMerge(Transformer transformer) throws TransformersException{
		checkInvalidTeam(transformer.getSpecs().getTeam());
		checkContent(transformer.getName());
	}

	private void checkContentNullKey(String name) throws TransformersException {
		if (name.equals(null)) {
			throw new TransformersException(HttpStatus.BAD_REQUEST, "Transformer must have a name");
		}
	}
	
	private void checkInvalidTeam(String team) throws TransformersException {
		if (team.equals("N")) {
			throw new TransformersException(HttpStatus.BAD_REQUEST, "Invalid parameter Team");
		}
	}

	private void checkDuplicatedContentKey(String name) throws TransformersException {
		if (transformerTable.containsKey(name)) {
			throw new TransformersException(HttpStatus.CONFLICT, "Transformer already inserted");
		}
	}

	private void checkContent(String name) throws TransformersException {
		if (!transformerTable.containsKey(name)) {
			throw new TransformersException(HttpStatus.NOT_FOUND, "Transformer not found");
		}
	}

}
