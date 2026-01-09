package com.example;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.File;

import io.xlate.edi.stream.EDIInputFactory;
import io.xlate.edi.stream.EDIStreamReader;

public class EDIParser {
    public static void main(String[] args) throws Exception {
        EDIInputFactory factory = EDIInputFactory.newFactory();

        // Obtain Stream to the EDI document to read.
        File file = new File("/Users/user/sourcecode/GitHub/mq-edifact-json/src/main/resources/nevdis.edi");
        InputStream stream = new FileInputStream(file);

        EDIStreamReader reader = factory.createEDIStreamReader(stream);

        while (reader.hasNext()) {
            switch (reader.next()) {
                case START_INTERCHANGE:
                    /* Retrieve the standard - "X12", "EDIFACT", or "TRADACOMS" */
                    String standard = reader.getStandard();

                    /*
                     * Retrieve the version string array. An array is used to support
                     * the componentized version element used in the EDIFACT standard.
                     *
                     * e.g. [ "00501" ] (X12) or [ "UNOA", "3" ] (EDIFACT)
                     */
                    String[] version = reader.getVersion();
                    break;

                case START_SEGMENT:
                    // Retrieve the segment name - e.g. "ISA" (X12), "UNB" (EDIFACT), or "STX"
                    // (TRADACOMS)
                    String segmentName = reader.getText();
                    System.out.println(segmentName);
                    break;

                case END_SEGMENT:
                    break;

                case START_COMPOSITE:
                    break;

                case END_COMPOSITE:
                    break;

                case ELEMENT_DATA:
                    // Retrieve the value of the current element
                    String data = reader.getText();
                    System.out.println(data);
                    break;
                case ELEMENT_DATA_BINARY:
                    break;
                case ELEMENT_DATA_ERROR:
                    break;
                case ELEMENT_OCCURRENCE_ERROR:
                    break;
                case END_GROUP:
                    break;
                case END_INTERCHANGE:
                    break;
                case END_LOOP:
                    break;
                case END_TRANSACTION:
                    break;
                case SEGMENT_ERROR:
                    break;
                case START_GROUP:
                    break;
                case START_LOOP:
                    break;
                case START_TRANSACTION:
                    break;
                default:
                    break;
            }
        }

        reader.close();
        stream.close();

    }

}
