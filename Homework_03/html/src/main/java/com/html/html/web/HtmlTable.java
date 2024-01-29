package com.html.html.web;
import static j2html.TagCreator.body;
import static j2html.TagCreator.div;
import static j2html.TagCreator.head;
import static j2html.TagCreator.html;
import static j2html.TagCreator.style;
import static j2html.TagCreator.table;
import static j2html.TagCreator.td;
import static j2html.TagCreator.title;
import static j2html.TagCreator.tr;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import j2html.tags.ContainerTag;

/**
 * A Java program to generate an HTML table and save it to a file.
 * The table contains course information for the Fall 2023 semester.
 * The generated HTML file is named 'hw3.html'.
 * It also opens the generated HTML file in the default web browser.
 * Author: Keerthika
 */
public class HtmlTable {
    public static void main(String[] args) {
        String fileName = "hw3.html";
        generateHTML();
        openHTMLFileInBrowser(fileName);
    }

    /**
     * This method generates an HTML file by adding the table and values into it.
     */
    public static void generateHTML() {
        try {
            FileWriter fileWriter = new FileWriter("hw3.html");
            PrintWriter printWriter = new PrintWriter(fileWriter);
            String coursePeriod = "Fall 2023";
            // Create a list of course names
            List<String> courseNames = Arrays.asList("Software Engineering", "Algorithm principles and design",
                    "Human-computer interaction", "User interface and user interaction");

            // Create the HTML content using J2HTML functions
            ContainerTag html = html(
                    head(title("My Fall 2023 schedule"), style(
                            "table, th, td { border: 3px solid black; font-size: 30px; } th { font-weight: bold; }")),
                    body().withStyle("display: flex; justify-content: center; align-items: center; height: 100vh;").with(
                            div(table().withStyle("width:100%")
                                    .with(tr(td().withText("Semester").withStyle("font-weight: bold;"), // Make it bold
                                            td().withText("Course").withStyle("font-weight: bold;") // Make it bold
                                    )).with(
                            // Use a loop to add rows for each course. Implemented a dry principle
                            courseNames.stream().map(courseName -> createTableRow(coursePeriod, courseName))
                                    .toArray(ContainerTag[]::new))
                            )));
            // Write the HTML content to the file
            printWriter.println(html.renderFormatted());

            // Close the PrintWriter and FileWriter
            printWriter.close();
            fileWriter.close();

            System.out.println("HTML file 'hw3.html' has been generated successfully.");
        } catch (IOException e) {
            System.err.println("Error writing to the file: " + e.getMessage());
        }
    }

    /**
     * Helper method to create a table row
     * 
     * @param cell1
     * @param cell2
     * @return
     */
    private static ContainerTag createTableRow(String cell1, String cell2) {
        return tr(td(cell1), td(cell2));
    }

    /**
     * * This method opens the generated HTML file in the web browser.
     * 
     * @param fileName
     */
    private static void openHTMLFileInBrowser(String fileName) {
        try {
            File htmlFile = new File(fileName);
            if (htmlFile.exists()) {

                // On Windows, use the "start" command to open the default web browser
                ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", "start", fileName);
                pb.directory(new File(System.getProperty("user.dir")));
                pb.start();

            } else {
                System.err.println("HTML file '" + fileName + "' not found.");
            }
        } catch (IOException e) {
            System.err.println("Error opening the HTML file: " + e.getMessage());
        }
    }
}
