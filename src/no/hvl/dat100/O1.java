package no.hvl.dat100;
import javax.swing.JOptionPane;

public class O1 {
    public static void main(String[] args) {
        boolean continueLoop = true;

        // Loop to keep asking for input until the user enters a valid score or cancels
        while (continueLoop) {
            // Show input dialog to get poengsummen (userScore)
            String input = JOptionPane.showInputDialog(null, "INPUT YOUR SCORE (0-100):", "SCORE INPUT", JOptionPane.QUESTION_MESSAGE);
            
            // If the user clicks "Cancel" or closes the dialog, break the loop
            
            if (input == null) {
                JOptionPane.showMessageDialog(null, "SYSTEM ERROR 453 - HitEscape - The program haw been terminated", "SYSERRROR 453", JOptionPane.INFORMATION_MESSAGE);
                break;
            }

            try {
                // Convert the input from String to int
                int userScore = Integer.parseInt(input);

                // Check if the score is within the allowed range (0 - 100)
                if (userScore < 0 || userScore > 100) {
                    JOptionPane.showMessageDialog(null, "ERROR: SCORE must be type=number that is ==<0 AND <= 100.", "INVALID SCORE", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Determine and show the grade
                    String gradeMessage;
                    if (userScore >= 95) {
                        gradeMessage = "Your final grade is A.And your score that was input is >> : "+ userScore;
                    } else if (userScore >= 85) {
                        gradeMessage = "Your final grade is B. And your score that was input is >> : "+ userScore;
                    } else if (userScore >= 70) {
                        gradeMessage = "Your final grade is C.And your score that was input is >> : "+ userScore;
                    } else if (userScore >= 60) {
                        gradeMessage = "Your final grade is D.And your score that was input is >> : "+ userScore;
                    } else if (userScore >= 50) {
                        gradeMessage = "Your final grade is E.And your score that was input is >> : "+ userScore;
                    } else if (userScore >= 40) {
                        gradeMessage = "Your final grade is F.And your score that was input is >> : "+ userScore;
                    } else {
                        gradeMessage = "Your final grade is under F.And your score that was input is >> : "+ userScore;
                    }
                    
                    // Show the result in a dialog box
                    JOptionPane.showMessageDialog(null, gradeMessage, "Resultat", JOptionPane.INFORMATION_MESSAGE);
                    
                    // After displaying result, ask if user wants to enter another score
                    int option = JOptionPane.showConfirmDialog(null, "Vil du beregne en annen poengsum?", "Fortsett?", JOptionPane.YES_NO_OPTION);
                    if (option == JOptionPane.NO_OPTION) {
                        continueLoop = false;  // Exit loop if user selects "No"
                    }
                }

            } catch (NumberFormatException e) {
                // Handle invalid input (not a number)
                JOptionPane.showMessageDialog(null, "Feil: Vennligst skriv inn et gyldig tall.", "Ugyldig input", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
