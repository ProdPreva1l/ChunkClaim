package info.preva1l.chunkclaim.data.settings;

import lombok.Getter;

import java.util.Arrays;

@Getter
public abstract class ClaimSetting  {
    private final SettingArgs args;
    public ClaimSetting() {
        this.args = Arrays.stream(this.getClass().getMethods()).filter(method -> method.getAnnotation(SettingArgs.class) != null).map(method -> method.getAnnotation(SettingArgs.class)).findFirst().orElse(null);
    }

    public void register() {
    }
}
