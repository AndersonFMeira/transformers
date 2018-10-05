package ca.eaq.transformers.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.eaq.transformers.models.BattleSummary;
import ca.eaq.transformers.models.ResponseObj;
import ca.eaq.transformers.models.Transformer;
import ca.eaq.transformers.service.TransformerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api("Set of endpoints for Creating, Retrieving, Updating and Deleting of Transformers and plays the battle game.")
public class TransformersController {

	@Autowired
	TransformerService service;

	@RequestMapping(value = "/transformers")
    @ApiOperation("Returns list of all transformers in the system.")
	public List<Transformer> listTransformers() {
		return service.listTransformers();
	}

	@RequestMapping(value = "/transformers", method = RequestMethod.POST)
	@ApiOperation("Creates a transformer.")
	public ResponseEntity<?> addTransformer(@RequestBody Transformer transformer) {
		try {
			service.addTransformer(transformer);
		} catch (TransformersException e) {
			return new ResponseEntity<ResponseObj>(new ResponseObj(e.getMessage()), e.getHttpStatus());
		}
		return new ResponseEntity<>("", HttpStatus.CREATED);
	}

	@RequestMapping(value = "/transformers", method = RequestMethod.PUT)
	@ApiOperation("Updates a transformers.")
	public ResponseEntity<?> updateTransformer(@RequestBody Transformer transformer) {
		try {
			service.changeTransformer(transformer);
		} catch (TransformersException e) {
			return new ResponseEntity<ResponseObj>(new ResponseObj(e.getMessage()), e.getHttpStatus());
		}
		return new ResponseEntity<>("", HttpStatus.OK);
	}

	@RequestMapping(value = "/transformers/{name}", method = RequestMethod.GET)
	@ApiOperation("Returns a transformer.")
	public ResponseEntity<?> findTransformer(@PathVariable String name) {
		Transformer transformer = service.findTransformer(name.toUpperCase());
		if (transformer.getName() == null) {
			return new ResponseEntity<ResponseObj>(new ResponseObj("Transformer not found"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Transformer>(transformer, HttpStatus.OK);
	}

	@RequestMapping(value = "transformers/{name}", method = RequestMethod.DELETE)
	@ApiOperation("Deletes a transformer.")
	public ResponseEntity<?> removeTransformer(@PathVariable String name) {
		try {
			service.deleteTransformer(name.toUpperCase());
		} catch (TransformersException e) {
			return new ResponseEntity<ResponseObj>(new ResponseObj(e.getMessage()), e.getHttpStatus());
		}
		return new ResponseEntity<>("", HttpStatus.OK);
	}

	@RequestMapping(value = "transformers/battle")
	@ApiOperation("Run and returns the results of the transformers battle.")
	public BattleSummary battle(@RequestParam List<String> names) {
		names.replaceAll(String::toUpperCase);
		return service.composeBattle(names);
	}

}
