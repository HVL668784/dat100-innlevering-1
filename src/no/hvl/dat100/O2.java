package no.hvl.dat100;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TrinnskattCalculator {

    // Trinnskatt-satser for 2024 (Norge)
    static double[][] trinnskattSatser = {
        {0, 208050, 0.00},      			// Trinn 0 - 0%
        {208051, 292850, 0.017}, 			// Trinn 1 - 1.7%
        {292851, 670000, 0.04}, 			// Trinn 2 - 4.0%
        {670001, 937900, 0.136}, 			// Trinn 3 - 13.6%
        {937901, 1350000, 0.166}, 			// Trinn 4 - 16.6%
        {1350001, Double.MAX_VALUE, 0.176} 	// Trinn 4 - 17.6%
    };

    // Språkvariabler (norsk, svensk, engelsk)
    static String language = "Norsk";
    static String[] languages = {"Norsk", "Svensk", "Engelsk"};

    public static void main(String[] args) {
        while (true) {
            // Vis dialogvindu for inntekt med språkvalg-knapp
            showIncomeDialog();
        }
    }

    // Funksjon for å vise dialogvindu med inntektsprompt og språkknapp
    public static void showIncomeDialog() {
        // Opprett panel for dialog
        JPanel panel = new JPanel(new BorderLayout());
        
        // Opprett tekstfelt for å angi inntekt
        JTextField incomeField = new JTextField(10);
        JLabel incomeLabel = new JLabel(getText("enterIncome"));
        panel.add(incomeLabel, BorderLayout.NORTH);
        panel.add(incomeField, BorderLayout.CENTER);
        
        // Opprett språkknapp
        JButton languageButton = new JButton(getText("changeLanguageButton"));
        panel.add(languageButton, BorderLayout.SOUTH);
        
        // Legg til actionlistener for språkknappen
        languageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chooseLanguage(); // La brukeren endre språk når knappen trykkes
                // Oppdater dialogvinduet umiddelbart med nytt språk
                incomeLabel.setText(getText("enterIncome"));
                languageButton.setText(getText("changeLanguageButton"));
            }
        });

        // Vis dialogen
        int result = JOptionPane.showConfirmDialog(null, panel, getText("title"), JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        
        if (result == JOptionPane.OK_OPTION) {
            try {
                // Hent inntekt fra tekstfeltet
                double income = Double.parseDouble(incomeField.getText());
                
                // Beregn trinnskatt
                double trinnskatt = calculateTrinnskatt(income);
                
                // Vis resultat i en ny dialog
                JOptionPane.showMessageDialog(null, String.format(getText("result"), income, trinnskatt), getText("resultTitle"), JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException e) {
                // Hvis inntastet verdi ikke er et gyldig nummer
                JOptionPane.showMessageDialog(null, getText("invalidNumber"), getText("errorTitle"), JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Funksjon for å la brukeren velge språk
    public static void chooseLanguage() {
        String choice = (String) JOptionPane.showInputDialog(null, "Velg språk / Välj språk / Choose language", "Språkvalg", JOptionPane.QUESTION_MESSAGE, null, languages, language);
        if (choice != null) {
            language = choice;
        }
    }

    // Funksjon for å hente riktig tekst basert på valgt språk
    public static String getText(String key) {
        switch (language) {
            case "Svensk":
                switch (key) {
                    case "enterIncome": return "Ange din årliga inkomst före skatt (SEK):";
                    case "title": return "Trinnskatt Calculator";
                    case "result": return "Baserat på din inkomst av %.2f SEK, är din beräknade trinnskatt %.2f SEK.";
                    case "resultTitle": return "Resultat";
                    case "invalidNumber": return "Vänligen ange en giltig inkomst som ett nummer.";
                    case "errorTitle": return "Fel";
                    case "changeLanguageButton": return "Byt språk";
                }
                break;

            case "Engelsk":
                switch (key) {
                    case "enterIncome": return "Enter your annual income before taxes (NOK):";
                    case "title": return "Trinnskatt Calculator";
                    case "result": return "Based on your income of %.2f NOK, your calculated trinnskatt is %.2f NOK.";
                    case "resultTitle": return "Result";
                    case "invalidNumber": return "Please enter a valid income as a number.";
                    case "errorTitle": return "Error";
                    case "changeLanguageButton": return "Change Language";
                }
                break;

            case "Norsk":
            default:
                switch (key) {
                    case "enterIncome": return "Hva er din årlige inntekt før skatt (NOK):";
                    case "title": return "Trinnskatt Kalkulator";
                    case "result": return "Basert på din inntekt på %.2f NOK, er din beregnede trinnskatt %.2f NOK.";
                    case "resultTitle": return "Resultat";
                    case "invalidNumber": return "Vennligst oppgi en gyldig inntekt som et tall.";
                    case "errorTitle": return "Feil";
                    case "changeLanguageButton": return "Endre språk";
                }
                break;
        }
        return "";
    }

    // Funksjon for å beregne trinnskatt
    public static double calculateTrinnskatt(double income) {
        double totalTrinnskatt = 0;

        // Gå gjennom hvert trinn og beregn trinnskatt
        for (double[] trinn : trinnskattSatser) {
            double lowerLimit = trinn[0];
            double upperLimit = trinn[1];
            double rate = trinn[2];

            if (income > lowerLimit) {
                double taxableIncome = Math.min(income, upperLimit) - lowerLimit;
                totalTrinnskatt += taxableIncome * rate;
            } else {
                break; // Hvis inntekten er lavere enn gjeldende trinn, stopper vi
            }
        }

        return totalTrinnskatt;
    }
}
