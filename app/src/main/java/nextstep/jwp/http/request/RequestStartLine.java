package nextstep.jwp.http.request;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;

public class RequestStartLine {

    private static final String QUERY_STRING_START = "?";
    private static final String QUERY_STRING_SPLIT = "&";
    private static final String KEY_VALUE_SPLIT = "=";

    private Method method;
    private String path;
    private HashMap<String, String> queryString;
    private String versionOfProtocol;

    private RequestStartLine(Method method, String path,
            HashMap<String, String> queryString, String versionOfProtocol) {
        this.method = method;
        this.path = path;
        this.queryString = queryString;
        this.versionOfProtocol = versionOfProtocol;
    }

    public static RequestStartLine create(BufferedReader bufferedReader) throws IOException {
        String[] splitLine = bufferedReader.readLine().split(" ");

        Method method = Method.valueOf(splitLine[0]);
        String path = splitLine[1];
        String versionOfProtocol = splitLine[2];
        HashMap<String, String> queryString = new HashMap<>();

        if (path.contains(QUERY_STRING_START)) {
            String uri = splitLine[1];
            int index = uri.indexOf(QUERY_STRING_START);
            path = uri.substring(0, index);
            queryString = queryString(uri.substring(index + 1));
        }

        return new RequestStartLine(method, path, queryString, versionOfProtocol);
    }

    private static HashMap<String, String> queryString(String uri) {
        HashMap<String, String> hashMap = new HashMap<>();

        String[] queries = uri.split(QUERY_STRING_SPLIT);

        for (String query : queries) {
            String[] keyValue = query.split(KEY_VALUE_SPLIT);
            hashMap.put(keyValue[0], keyValue[1]);
        }

        return hashMap;
    }

    public Method getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }

    public String getAttribute(String name) {
        return queryString.get(name);
    }

    public String getVersionOfProtocol() {
        return versionOfProtocol;
    }
}