public class miniTwitterMethods {

    void LoadingAnim(String startingWord) {
        System.out.print(startingWord);
        for (int i = 0; i < 3; i++) {
            try {
                Thread.sleep(500);
                System.out.print(".");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    void CodeInvalid(String errorName) {
        System.out.println();
        System.out.println("ERROR: Code is Invalid!");
        System.out.println("Error found on " + errorName);
        System.out.println();
    }
    void NumericalError(String errorName) {
        System.out.println();
        System.out.println("ERROR 04: Input should only contain numerical characters!");
        System.out.println("Error found on " + errorName);
        System.out.println();
    }
    void UsernameInvalid(String errorName) {
        System.out.println();
        System.out.println("ERROR: Username should be atleast 5 characters!");
        System.out.println("Error found on " + errorName);
        System.out.println();
    }
    void PasswordInvalid(String errorName) {
        System.out.println();
        System.out.println("ERROR: Password should contain alphanumerical characters and atleast 8 characters!");
        System.out.println("Error found on " + errorName);
        System.out.println();
    }
    void MonthInvalid(String errorName) {
        System.out.println();
        System.out.println("ERROR: Month only ranges from 1-12!");
        System.out.println("Error found on " + errorName);
        System.out.println();
    }

    void YearInvalid(String errorName) {
        System.out.println();
        System.out.println("ERROR: Current Year is 2023!");
        System.out.println("Error found on " + errorName);
        System.out.println();
    }
    void DayInvalid(String errorName) {
        System.out.println();
        System.out.println("ERROR: Invalid Day!");
        System.out.println("Error found on " + errorName);
        System.out.println();
    }


}
