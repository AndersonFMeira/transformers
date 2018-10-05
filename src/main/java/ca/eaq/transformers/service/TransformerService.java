package ca.eaq.transformers.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.eaq.transformers.controller.TransformersException;
import ca.eaq.transformers.dao.TransformerDAO;
import ca.eaq.transformers.models.BattleField;
import ca.eaq.transformers.models.BattleSummary;
import ca.eaq.transformers.models.Transformer;

@Service
public class TransformerService {

	private final String AUTOBOT_TAG = "A";
	private final String DECEPTICON_TAG = "D";

	@Autowired
	TransformerDAO tDao;

	public List<Transformer> listTransformers() {
		return tDao.findTransformers();
	}
	
	public Transformer findTransformer(String name){
		return tDao.findTransformer(name);
	}

	public void addTransformer(Transformer transformer) throws TransformersException {
		tDao.persistTransformer(transformer);
	}

	public void changeTransformer(Transformer transformer) throws TransformersException {
		tDao.mergeTransformer(transformer);
	}

	public void deleteTransformer(String name) throws TransformersException {
		tDao.removeTransformer(name);
	}

	public BattleSummary composeBattle(List<String> names) {
		List<Transformer> transformers = tDao.findTransformersById(names);
		List<Transformer> autobots = composeTeam(transformers, AUTOBOT_TAG);
		List<Transformer> decepticons = composeTeam(transformers, DECEPTICON_TAG);
		BattleField battleField = new BattleField(sortTeam(autobots), sortTeam(decepticons));
		battleField.startBattle();
		return battleField.getBattleSummary();
	}

	private List<Transformer> composeTeam(List<Transformer> transformers, String team) {
		return transformers.stream().filter(t -> t.getSpecs().getTeam().equals(team))
				.filter(t -> t.getSpecs().getTeam().equals(team)).collect(Collectors.toList());
	}

	private List<Transformer> sortTeam(List<Transformer> transformers) {
		transformers.sort((t1, t2) -> Integer.compare(t1.getSpecs().getRank(), t2.getSpecs().getRank()));
		return transformers;
	}

}
