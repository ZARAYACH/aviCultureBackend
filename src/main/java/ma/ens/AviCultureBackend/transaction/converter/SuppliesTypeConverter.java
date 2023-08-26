package ma.ens.AviCultureBackend.transaction.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class SuppliesTypeConverter implements AttributeConverter<List<String>, String> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(List<String> strings) {
        if (strings != null && strings.isEmpty()) {
            return "[]";
        }
        try {
            return objectMapper.writeValueAsString(strings);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "[]";
        }
    }

    @Override
    public List<String> convertToEntityAttribute(String s) {
        if (StringUtils.isBlank(s)) {
            return new ArrayList<>();
        }
        try {
            return objectMapper.readValue(s, new TypeReference<List<String>>() {});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
