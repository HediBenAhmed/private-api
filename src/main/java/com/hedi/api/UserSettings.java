package com.hedi.api;

import java.util.Map;
import lombok.Data;

@Data
public class UserSettings {
    private Map<String, Boolean> features;
}
