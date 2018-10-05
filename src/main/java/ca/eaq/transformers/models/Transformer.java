package ca.eaq.transformers.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Class representing a Transformer.")
public class Transformer {

    @ApiModelProperty(notes = "Unique identifier of a transformer. Two transformers can't have the same id.", example = "Bumblebee", required = true, position = 0)
	private String name;
    @ApiModelProperty(notes = "Transformer specifications.", required = true, position = 1)
	private TransformerSpecs specs = new TransformerSpecs();
	
	public Transformer() {
		super();
	}

	public Transformer(String name, TransformerSpecs specs) {
		setName(name);
		this.specs = specs;
	}

    @ApiModelProperty(notes = "A combination of specs that often define who wins the battle.", position = 2)
	public int getOverallRating(){
		return specs.getStrength() + specs.getIntelligence() + 
				specs.getSpeed() + specs.getEndurance() + specs.getFirepower();
	}
	
	public TransformerSpecs getSpecs() {
		return specs;
	}
	
	public void setSpecs(TransformerSpecs specs) {
		this.specs = specs;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		if(!name.isEmpty()){
			this.name = name.toUpperCase();			
		} 
	}
	
}
