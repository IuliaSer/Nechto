package nechto;

import com.fasterxml.jackson.databind.SequenceWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import nechto.dto.request.RequestUserDto;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static nechto.enums.Authority.ROLE_USER;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
        CsvMapper csvMapper = new CsvMapper();

        // Configure CsvMapper to exclude null values
//        csvMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        // Define schema based on the DTO class
        CsvSchema schema = csvMapper.schemaFor(RequestUserDto.class).withHeader();

        // Sample data
        List<RequestUserDto> persons = Arrays.asList(
                new RequestUserDto(null, "Alice", "pass", ROLE_USER),
                new RequestUserDto(null, "Bob", "25", ROLE_USER)); // Last name is null, should be excluded


        // Write to CSV file
        File file = new File("persons.csv");
        try (FileWriter writer = new FileWriter(file);
             SequenceWriter seqWriter = csvMapper.writer(schema).writeValues(writer)) {
            seqWriter.writeAll(persons);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("CSV file created successfully!");
    }
}
