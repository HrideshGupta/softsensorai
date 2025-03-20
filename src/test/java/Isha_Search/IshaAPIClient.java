package Isha_Search;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import java.io.IOException;
import java.util.List;

// Main class to send request and parse response
public class IshaAPIClient {

    private static final String API_URL = "https://ask-api-prod.sadhguru.org/v1/ask-sadhguru";

    public static void main(String[] args) {
        try {
            String jsonResponse = sendQuestion("What is Sadhana?");
            APIResponse response = parseResponse(jsonResponse);
            
            // Print the parsed response
            System.out.println("Intent Class Code: " + response.getIntentClassCode());
            System.out.println("Corpus Code: " + response.getCorpusCode());
            System.out.println("\nAnswers:");
            for (Answer answer : response.getAnswers()) {
                System.out.println("Text: " + answer.getText());
                System.out.println("Chunk ID: " + answer.getChunkId());
                System.out.println("Asset ID: " + answer.getAssetId());
                System.out.println("Asset Type: " + answer.getAssetType());
                System.out.println("Timestamp: " + answer.getTimestamp());
                System.out.println("Source URL: " + answer.getSourceUrl());
                System.out.println("---");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to send HTTP request
    private static String sendQuestion(String question) throws IOException, ParseException {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost request = new HttpPost(API_URL);
            request.setHeader("Content-Type", "application/json");

            // JSON Payload
            String jsonPayload = "{\"question\": \"" + question + "\", \"language\": \"en\"}";
            request.setEntity(new StringEntity(jsonPayload));

            try (CloseableHttpResponse response = httpClient.execute(request)) {
                return EntityUtils.toString(response.getEntity());
            }
        }
    }

    // Method to parse JSON response
    private static APIResponse parseResponse(String jsonResponse) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(jsonResponse, APIResponse.class);
    }
}

// --- Entity Classes ---

// API Response Mapping
@JsonIgnoreProperties(ignoreUnknown = true)
class APIResponse {
    @JsonProperty("intent_class_code")
    private int intentClassCode;

    @JsonProperty("intent_class_sub_code")
    private int intentClassSubCode;

    @JsonProperty("corpus_code")
    private int corpusCode;

    @JsonProperty("answers")
    private List<Answer> answers;

    // Getters and Setters
    public int getIntentClassCode() { return intentClassCode; }
    public void setIntentClassCode(int intentClassCode) { this.intentClassCode = intentClassCode; }

    public int getIntentClassSubCode() { return intentClassSubCode; }
    public void setIntentClassSubCode(int intentClassSubCode) { this.intentClassSubCode = intentClassSubCode; }

    public int getCorpusCode() { return corpusCode; }
    public void setCorpusCode(int corpusCode) { this.corpusCode = corpusCode; }

    public List<Answer> getAnswers() { return answers; }
    public void setAnswers(List<Answer> answers) { this.answers = answers; }
}

// Answer Mapping
@JsonIgnoreProperties(ignoreUnknown = true)
class Answer {
    @JsonProperty("text")
    private String text;

    @JsonProperty("chunk_id")
    private String chunkId;

    @JsonProperty("asset_id")
    private String assetId;

    @JsonProperty("asset_type")
    private String assetType;

    @JsonProperty("timestamp")
    private String timestamp;

    @JsonProperty("source_url")
    private String sourceUrl;

    // Getters and Setters
    public String getText() { return text; }
    public void setText(String text) { this.text = text; }

    public String getChunkId() { return chunkId; }
    public void setChunkId(String chunkId) { this.chunkId = chunkId; }

    public String getAssetId() { return assetId; }
    public void setAssetId(String assetId) { this.assetId = assetId; }

    public String getAssetType() { return assetType; }
    public void setAssetType(String assetType) { this.assetType = assetType; }

    public String getTimestamp() { return timestamp; }
    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }

    public String getSourceUrl() { return sourceUrl; }
    public void setSourceUrl(String sourceUrl) { this.sourceUrl = sourceUrl; }
}
