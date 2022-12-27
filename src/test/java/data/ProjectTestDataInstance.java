package data;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.as.autot.core.common.Constants;
import com.as.autot.utility.JsonDataMasaging;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import data.Quote.Vessel;
import data.Quote.Vessels;

public class ProjectTestDataInstance {

	public static final ObjectMapper MAPPER = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

	private static final Logger LOGGER =  LogManager.getLogger(ProjectTestDataInstance.class);
	
	public static void main(String[] args) {
		Quote q = new Quote();
		q = (Quote) getTestData("quote.json", q);

		readQuote(q);

	}

	private static void readQuote(Quote q) {
		List<Vessel> vessels = q.vessels().vesselList();
		for (int i = 0; i < q.vessels().vesselList().size(); i++) {
			LOGGER.debug(i);
			Vessel vessel = vessels.get(i);
			LOGGER.debug(vessel);
			List<String> navigationAreas = vessel.vesselPricingDetail().navigationAreas();
			LOGGER.debug(navigationAreas.get(0));
		}
	}

	public static Object getTestData(String jsonDataPath, Object object) {
		try {
			MAPPER.getTypeFactory().constructCollectionType(List.class, Vessels.class);
			object = MAPPER.readValue(
					Files.readString(Paths.get(Constants.TEST_DATA_REPOSITORY + jsonDataPath), StandardCharsets.UTF_8),
					object.getClass());
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
		}
		return object;
	}

	public static Object getTestDataAfterMassage(String jsonDataPath, Object object) {
		try {
			String jsonData = JsonDataMasaging.getJsonMassaged(jsonDataPath);
			MAPPER.getTypeFactory().constructCollectionType(List.class, Vessels.class);
			object = MAPPER.readValue(jsonData, object.getClass());
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
		}
		return object;
	}
}
