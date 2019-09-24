package hospital.service;


import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author Oleksandr Belichenko
 */
@Service
public class GeneralService {

    final static Logger logger = LoggerFactory.getLogger(GeneralService.class);

    public boolean isValidStringData(Object sValue) {
        if (Objects.nonNull(sValue)) {

            return StringUtils.isNotEmpty((String) sValue)
                    && !"null".equalsIgnoreCase(sValue.toString());
        }

        return false;
    }

    public List<String> getListFromString(String arrayInString) {
        List<String> result = Arrays.asList(arrayInString.split("\\s*,\\s*"));
        return result;
    }

    public DateTime convertStringToDateTime(String date){
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");
        return formatter.parseDateTime(date);
    }
}
