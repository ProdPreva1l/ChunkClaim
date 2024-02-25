package info.preva1l.chunkclaim.data.settings;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ClaimSettingsManager {
    private final List<ClaimSetting> loadedSettings = new ArrayList<>();

    public ClaimSettingsManager() {
    }

    public void register() {

    }

}
