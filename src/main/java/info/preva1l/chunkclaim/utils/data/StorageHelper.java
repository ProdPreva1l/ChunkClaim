package info.preva1l.chunkclaim.utils.data;

import lombok.experimental.UtilityClass;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.HashMap;

@UtilityClass
public class StorageHelper {
    public String serializeID(String server, Chunk chunk) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("server", server);
        jsonObject.put("x", chunk.getX());
        jsonObject.put("z", chunk.getZ());
        jsonObject.put("world", chunk.getWorld().getName());
        return jsonObject.toJSONString();
    }
    public HashMap<String, Chunk> deserializeID(String claimID) {
        JSONObject jsonObject;
        HashMap<String, Chunk> hashMap = new HashMap<>();
        try {
            jsonObject = (JSONObject) new JSONParser().parse(claimID);
            hashMap.put(jsonObject.get("server").toString(), Bukkit.getWorld(jsonObject.get("world").toString()).getChunkAt(Integer.valueOf(jsonObject.get("x").toString()), Integer.valueOf(jsonObject.get("z").toString())));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return hashMap;
    }
}
