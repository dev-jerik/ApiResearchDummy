package com.courtesypoint.apiresearch.draft;

public class GmailQuickstart {
//    private static final String APPLICATION_NAME = "Gmail API Java Quickstart";
//    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
//    private static final String TOKENS_DIRECTORY_PATH = "tokens";
//
//    /**
//     * Global instance of the scopes required by this quickstart.
//     * If modifying these scopes, delete your previously saved tokens/ folder.
//     */
//    private static final List<String> SCOPES = Collections.singletonList(GmailScopes.GMAIL_LABELS);
//    private static final String CREDENTIALS_FILE_PATH = "/credentials.json";
//
//    /**
//     * Creates an authorized Credential object.
//     * @param HTTP_TRANSPORT The network HTTP Transport.
//     * @return An authorized Credential object.
//     * @throws IOException If the credentials.json file cannot be found.
//     */
//    private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
//        // Load client secrets.
//        InputStream in = GmailQuickstart.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
//        if (in == null) {
//            throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
//        }
//        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));
//
//        // Build flow and trigger user authorization request.
//        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
//                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
//                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
//                .setAccessType("offline")
//                .build();
//        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
//        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
//    }
//
//    public static void main(String... args) throws IOException, GeneralSecurityException {
//        // Build a new authorized API client service.
//        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
//        Gmail service = new Gmail.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
//                .setApplicationName(APPLICATION_NAME)
//                .build();
//        // Print the labels in the user's account.
//        String user = "me";
//        ListLabelsResponse listResponse = service.users().labels().list(user).execute();
//        List<Label> labels = listResponse.getLabels();
//        if (labels.isEmpty()) {
//            System.out.println("No labels found.");
//        } else {
//            System.out.println("Labels:");
//            for (Label label : labels) {
//                System.out.printf("- %s\n", label.getName());
//            }
//        }
//    }
}