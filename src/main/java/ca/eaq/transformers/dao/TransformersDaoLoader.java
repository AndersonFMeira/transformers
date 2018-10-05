package ca.eaq.transformers.dao;

import java.util.HashMap;
import java.util.Map;

import ca.eaq.transformers.models.TransformerSpecs;

public class TransformersDaoLoader {

	/**
	 *  this class generate mock data to start the "database" 
	 * 
	 */
	
	public static Map<String, TransformerSpecs> loadTransformers() {

		Map<String, TransformerSpecs> transformersBuild = new HashMap<String, TransformerSpecs>();

		TransformerSpecs courageUpA = new TransformerSpecs("A", 10, 1, 1, 1, 1, 10, 1, 1);
		TransformerSpecs aSkill = new TransformerSpecs("A", 4, 4, 4, 4, 4, 4, 4, 10);
		TransformerSpecs aTie = new TransformerSpecs("A", 2, 2, 2, 2, 2, 2, 2, 2);
		TransformerSpecs dTie = new TransformerSpecs("D", 2, 2, 2, 2, 2, 2, 2, 2);
		TransformerSpecs dweak = new TransformerSpecs("D", 1, 1, 1, 1, 1, 1, 1, 1);

		TransformerSpecs as1 = new TransformerSpecs("A", 1, 1, 1, 1, 1, 1, 1, 1);
		TransformerSpecs as2 = new TransformerSpecs("A", 2, 2, 2, 2, 2, 2, 2, 2);
		TransformerSpecs as3 = new TransformerSpecs("A", 3, 3, 3, 3, 3, 3, 3, 3);
		TransformerSpecs as4 = new TransformerSpecs("A", 4, 4, 4, 4, 4, 4, 4, 4);

		TransformerSpecs ds1 = new TransformerSpecs("D", 1, 1, 1, 1, 1, 1, 1, 1);
		TransformerSpecs ds2 = new TransformerSpecs("D", 2, 2, 2, 2, 2, 2, 2, 2);
		TransformerSpecs ds3 = new TransformerSpecs("D", 3, 3, 3, 3, 3, 3, 3, 3);
		TransformerSpecs ds4 = new TransformerSpecs("D", 4, 4, 4, 4, 4, 4, 4, 4);
		TransformerSpecs ds5 = new TransformerSpecs("D", 5, 5, 5, 5, 5, 5, 5, 5);
		TransformerSpecs ds6 = new TransformerSpecs("D", 2, 2, 2, 2, 2, 2, 2, 2);
		TransformerSpecs ds7 = new TransformerSpecs("D", 2, 2, 2, 2, 2, 2, 2, 2);
		TransformerSpecs ds8 = new TransformerSpecs("D", 2, 2, 2, 2, 2, 2, 2, 2);
		TransformerSpecs ds9 = new TransformerSpecs("D", 1, 1, 5, 1, 1, 6, 1, 1);
		TransformerSpecs ds10 = new TransformerSpecs("D", 2, 2, 2, 8, 2, 2, 2, 2);

		transformersBuild.put("OPTIMUS PRIME", as1);
		transformersBuild.put("ATIE", aTie);
		transformersBuild.put("ABRAVE", courageUpA);
		transformersBuild.put("ASKILL", aSkill);
		transformersBuild.put("A2", as2);
		transformersBuild.put("A3", as3);
		transformersBuild.put("A4", as4);
		transformersBuild.put("A5", as4);
		transformersBuild.put("A6", as4);
		transformersBuild.put("A7", as4);
		transformersBuild.put("A8", as4);
		transformersBuild.put("A9", as4);
		transformersBuild.put("A10", as4);

		transformersBuild.put("PREDAKING", ds5);
		transformersBuild.put("DTIE", dTie);
		transformersBuild.put("DWEAK", dweak);
		transformersBuild.put("D1", ds1);
		transformersBuild.put("D2", ds2);
		transformersBuild.put("D3", ds3);
		transformersBuild.put("D4", ds4);
		transformersBuild.put("D5", ds5);
		transformersBuild.put("D6", ds6);
		transformersBuild.put("D7", ds7);
		transformersBuild.put("D8", ds8);
		transformersBuild.put("D9", ds9);
		transformersBuild.put("D10", ds10);

		return transformersBuild;

	}

}
