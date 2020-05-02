package de.schulze.csv;

import de.schulze.csv.assets.Decoder;
import de.schulze.csv.assets.Loader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class CsvApplication {



	public static void main(String[] args)
	{
		SpringApplication csv = new SpringApplication();

		Map<String, Object> properties = new HashMap<>();
		properties.put("spring.resources.static-locations", ".\\res\\css");
		properties.put("server.port", "80");

		csv.setDefaultProperties(properties);
		SpringApplication.run(CsvApplication.class, args);

		try {
			String[][] data = new Decoder().decode(new Loader().loadFile(new File("G:\\test2.csv")),true);

			for(int i = 0; i < data.length; i++)
			{
				for(int j = 0; j < data[i].length; j++) System.out.print(data[i][j] + " ");
				System.out.println();
			}
			data = new Decoder().decode(new Loader().loadFile(new File("G:\\test.csv")),false);

			for(int i = 0; i < data.length; i++)
			{
				for(int j = 0; j < data[i].length; j++) System.out.print(data[i][j] + " ");
				System.out.println();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
