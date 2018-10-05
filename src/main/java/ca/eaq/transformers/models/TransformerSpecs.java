package ca.eaq.transformers.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Class representing a Transformer specs.")
public class TransformerSpecs {

	private final int MAX_SPEC = 10;
	private final int MIN_SPEC = 1;
	
    @ApiModelProperty(notes = "Defines which team a transformer belongs. A (Autoboot) D (Decepticon)", example = "A", required = true, position = 0)
	private String team;
    @ApiModelProperty(notes = "Build overall rating (min:1 max:10)", example = "10", required = true, position = 1)
	private int strength;
    @ApiModelProperty(notes = "Build overall rating. Combined with courage can define the battle (min:1 max:10)", example = "10", required = true, position = 2)
	private int intelligence;
    @ApiModelProperty(notes = "Build overall rating (min:1 max:10)", example = "10", required = true, position = 3)
	private int speed;
    @ApiModelProperty(notes = "Used to build overall rating (min:1 max:10)", example = "10", required = true, position = 4)
	private int endurance;
    @ApiModelProperty(notes = "Define the order in battle (min:1 max:10)", example = "1", required = true, position = 5)
	private int rank;
    @ApiModelProperty(notes = "Combined with strength can define the battle (min:1 max:10)", example = "1", required = true, position = 6)
	private int courage;
    @ApiModelProperty(notes = "Build overall rating (min:1 max:10)", example = "10", required = true, position = 7)
	private int firepower;
    @ApiModelProperty(notes = "Can define the battle (min:1 max:10)", example = "1", required = true, position = 8)
	private int skill;
	
	public TransformerSpecs() {
		this.team = "N";
	}

	public TransformerSpecs(String team, int strength, int intelligence, int speed, int endurance, int rank,
			int courage, int firepower, int skill) {
		super();
		this.team = team;
		this.strength = strength;
		this.intelligence = intelligence;
		this.speed = speed;
		this.endurance = endurance;
		this.rank = rank;
		this.courage = courage;
		this.firepower = firepower;
		this.skill = skill;
	}
	
	public String getTeam() {
		return team.toUpperCase();
	}
	
	public void setTeam(String team) {
		if(team.toUpperCase().equals("A") || team.toUpperCase().equals("D")){
			this.team = team.toUpperCase();			
		} 
	}

	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = validateSpec(strength);
	}

	public int getIntelligence() {
		return intelligence;
	}

	public void setIntelligence(int intelligence) {
		this.intelligence = validateSpec(intelligence);
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = validateSpec(speed);
	}

	public int getEndurance() {
		return endurance;
	}

	public void setEndurance(int endurance) {
		this.endurance = validateSpec(endurance);
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = validateSpec(rank);
	}

	public int getCourage() {
		return courage;
	}

	public void setCourage(int courage) {
		this.courage = validateSpec(courage);
	}

	public int getFirepower() {
		return firepower;
	}

	public void setFirepower(int firepower) {
		this.firepower = validateSpec(firepower);
	}

	public int getSkill() {
		return skill;
	}

	public void setSkill(int skill) {
		this.skill = validateSpec(skill);
	}
	
	private int validateSpec(int spec){
		if(spec < MIN_SPEC){
			spec = MIN_SPEC;
		} else if( spec > MAX_SPEC){
			spec = MAX_SPEC;
		}
		return spec;
	}

}