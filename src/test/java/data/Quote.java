package data;

import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;


/**
 * This class is representing Quote and its corresponding record objects
 * 
 * @author Anuj Tripathi
 *
 */

public record Quote(String template, String quoteCreateDate, PolicyDates policyDates, MemberDetails memberDetails,
		 Vessels vessels, Instalment instalment, PriceSection priceSection) implements Serializable {

	private static final Logger LOGGER =  Logger.getLogger(Quote.class);
	
	public Quote() {
		this(null, null, null, null, null, null, null);
	}
	
	public record PriceSection(String totalGrossPremium) implements Serializable {
	}

	public record Instalment(String percentage) implements Serializable{
	}

	public record MemberDetails(boolean isComfirmed) implements Serializable{
	}

	public record PolicyDates(String startDate, String endDate, boolean isConfirmed) implements Serializable{
	}

	
	public record Vessels(List<Vessel> vesselList) {

	}
	
	public record Vessel(VesselBasicDetail vesselDetail, VesselPricingDetail vesselPricingDetail) implements Serializable{

		public Vessel() {
			this(null, null);
		}
	}

	public record VesselBasicDetail(String name, String imo, String refNo, String loa, String submarine,
			String distinctiveNo, String yearBuild) implements Serializable{
	}

	public record VesselPricingDetail(String usage, String gt, String flag, String crew, String usaRegistered,
			List<String> navigationAreas, String waterSports, String racing, String currency) implements Serializable{
		
	}

	public static void main(String[] args) throws IOException {
		createQuote( "quote.json");
	}

	/**
	 * To create quote.json with provided details
	 * This will help to create different flavor of test data for quotes
	 * @param path
	 * @return
	 * @throws IOException
	 */
	private static Quote createQuote(String path) throws IOException {
		PolicyDates policyDates = new PolicyDates("02/12/2022", "02/12/2023", true);
		MemberDetails MemberDetails = new MemberDetails(true);

		ArrayList<Vessel> vessels = new ArrayList<Vessel>();
		VesselBasicDetail vesselDetail1 = new VesselBasicDetail("Vessel_123", "imo123", "ref123", "5", "5", "2", "1982");
		List<String> navigationAreas1 = new ArrayList<String>();
		navigationAreas1.add("Worldwide");
		VesselPricingDetail vesselPricingDetail1 = new VesselPricingDetail("Private pleasure only", "500", "ALBANIA",
				"25", "Yes", navigationAreas1, "Yes", "Yes", "EUR");
		Vessel vessel1 = new Vessel(vesselDetail1, vesselPricingDetail1);

		VesselBasicDetail vesselDetail2 = new VesselBasicDetail("Vessel_xyz", "imo235", "ref23423", "4", "3", "1", "1800");
		List<String> navigationAreas2 = new ArrayList<String>();
		navigationAreas2.add("Worldwide");
		VesselPricingDetail vesselPricingDetail2 = new VesselPricingDetail("Chartering", "500", "INDIA", "25", "Yes",
				navigationAreas2, "Yes", "Yes", "EUR");
		Vessel vessel2 = new Vessel(vesselDetail2, vesselPricingDetail2);
		vessels.add(vessel1);
		vessels.add(vessel2);
		Instalment instalment = new Instalment("Yearly");
		PriceSection priceSection = new PriceSection("12000");
		
		Vessels vesselList = new Vessels(vessels);
		Quote quote = new Quote("Yacth", "02/12/2022", policyDates, MemberDetails, vesselList , instalment, priceSection);

		final ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
		
		String serialized = mapper.writeValueAsString(quote).toString();
		Files.writeString(Paths.get(path), serialized, StandardCharsets.UTF_8);
		
		mapper.getTypeFactory().constructCollectionType(List.class, Vessels.class);
		Quote deserialized = mapper.readValue(Files.readString(Paths.get(path),StandardCharsets.UTF_8), Quote.class);
		LOGGER.debug(deserialized);
		return deserialized;
		
	}
	
	
}
