
public class ResourceUtils {

    public static String badRequest(String request, String message) {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("\"error\":{");
        sb.append("\"request\":\"").append(request).append("\",");
        sb.append("\"code\":400,");
        sb.append("\"type\":\"bad request\",");
        sb.append("\"message\":").append("\"").append(message).append("\"");
        sb.append("}");
        sb.append("}");
        return sb.toString();
    }

    public static String notFound(String request, String message) {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("\"error\":{");
        sb.append("\"request\":\"").append(request).append("\",");
        sb.append("\"code\":404,");
        sb.append("\"type\":\"not found\",");
        sb.append("\"message\":").append("\"").append(message).append("\"");
        sb.append("}");
        sb.append("}");
        return sb.toString();
    }

    public static String internalError(String request, String message) {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("\"error\":{");
        sb.append("\"request\":\"").append(request).append("\",");
        sb.append("\"code\":500,");
        sb.append("\"type\":\"internal error\",");
        sb.append("\"message\":").append("\"").append(message).append("\"");
        sb.append("}");
        sb.append("}");
        return sb.toString();
    }

    public static String successId(int id) {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("\"data\":{");
        sb.append("\"id\":").append(id);
        sb.append("}");
        sb.append("}");
        return sb.toString();
    }

    public static String unauthorized(String request, String message) {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("\"error\":{");
        sb.append("\"request\":\"").append(request).append("\",");
        sb.append("\"code\":401,");
        sb.append("\"type\":\"unauthorized\",");
        sb.append("\"message\":").append("\"").append(message).append("\"");
        sb.append("}");
        sb.append("}");
        return sb.toString();
    }
}
