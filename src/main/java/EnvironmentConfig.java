public class EnvironmentConfig {

    public static String getURL(){
        String sysProp = System.getProperty("target.base.url");

        if (sysProp != null && !sysProp.isEmpty()){
            return sysProp;
        }

        String envVar = System.getenv("TARGET_BASE_URL");
        if (envVar != null && !envVar.isEmpty()){
            return envVar;
        }

        return "http://localhost:8080";
    }

    public static int getTimeoutSeconds() {
        return Integer.parseInt(System.getenv().getOrDefault("API_TIMEOUT_SEC", "10"));
    }
}
