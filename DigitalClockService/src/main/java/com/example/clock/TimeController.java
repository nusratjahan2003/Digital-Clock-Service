
package com.example.clock;

import org.springframework.web.bind.annotation.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
@RequestMapping("/api/time")
@CrossOrigin
public class TimeController {

    @GetMapping
    public Map<String, String> getTime(
        @RequestParam(defaultValue = "UTC") String timezone,
        @RequestParam(defaultValue = "24") String format
    ) {
        Map<String, String> response = new HashMap<>();
        try {
            ZonedDateTime now = ZonedDateTime.now(ZoneId.of(timezone));
            DateTimeFormatter formatter = format.equals("12") ?
                DateTimeFormatter.ofPattern("hh:mm:ss a") :
                DateTimeFormatter.ofPattern("HH:mm:ss");
            response.put("time", now.format(formatter));
            response.put("timezone", timezone);
        } catch (Exception e) {
            response.put("error", "Invalid timezone");
        }
        return response;
    }
}
