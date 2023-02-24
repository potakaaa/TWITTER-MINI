import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class miniTwitter {
    static final String databaseURL = "jdbc:mysql://localhost:3306/mini_twitter_database";
    static final String username = "root";
    static final String password = "mySql123#";

    public static void main(String[] args) {
        miniTwitterMethods methods = new miniTwitterMethods();
        Scanner sc = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);
        Scanner sc3 = new Scanner(System.in);
        Scanner sc4 = new Scanner(System.in);

        try {
            Connection con = DriverManager.getConnection(databaseURL, username, password);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from mini_twitter_accounts");
            System.out.println();
            System.out.println("+------TwitterMini-------+");
            System.out.println("|  CODE  |     OPTION    |");
            System.out.println("+------------------------+");
            System.out.println("|   LI   |     LOG IN    |");
            System.out.println("|   SU   |     SIGN UP   |");
            System.out.println("+------------------------+");
            System.out.println();

            boolean boolLiOrSu = false;
            // SIGNUP VARIABLES
            String newUsername = ""; String newPassword = ""; int newYear = 0; int newMonth = 0; int newDay = 0; String newBirthday = ""; String newSecuQuestion = ""; String newSecuAnswer = "";
            int newFollowers = 0; int newFollowing = 0;

            // ARRAY FOR ACCOUNT STORAGE
            String[] exisUsername = new String[50]; int x = 0; String[] exisPassword = new String[50]; String[] exisBirthday = new String[50]; String[] exisSecuQuestion = new String[50]; String[] exisSecuAnswer = new String[50];
            int[] exisID = new int[50]; int[] exisFollowers = new int[50]; int[] exisFollowing = new int[50];

            // LOGIN VARIABLES
            String logUsername = ""; String logPassword = ""; String logBirthday = ""; String logSecuQuestion = ""; String logSecuAnswer = ""; int logId = 0; String logPasswordValidator = "";
            int logFollowers = 0; int logFollowing = 0;

            // INTERACT VARIABLES
            int userId = 0; String userName = ""; String userBirthday = ""; int userFollowers = 0; int userFollowing = 0; String interactPost = ""; int interactTweet = 0; int interactLikes = 0; int interactRetweets = 0;
            int[] userPostID = new int[50]; String[] userPost = new String[50]; int[] userPostLikes = new int[50]; int[] userPostRetweets = new int[50];


            while(rs.next()) {
                exisUsername[x] = rs.getString("account_username");
                x++;
            }

            while (!boolLiOrSu) {
                System.out.print("Enter Code: ");
                String liOrSu = sc.nextLine();
                System.out.println();

                if (liOrSu.equalsIgnoreCase("su")) {
                    methods.LoadingAnim("Loading");
                    boolean boolNewUsername = false; boolean boolUserValidator = false;
                    while (!boolNewUsername) {
                        System.out.print("Enter New Username: ");
                        newUsername = sc.nextLine();

                        if (newUsername.length() > 5) {
                            for (int i = 0; i < exisUsername.length; i++) {
                                if (newUsername.equals(exisUsername[i])) {
                                    System.out.println("Username Already Exists!");
                                    System.out.println();
                                } else {
                                    boolUserValidator = true;
                                }
                            } if (boolUserValidator) {
                                boolNewUsername = true;
                            }
                        } else {
                            methods.UsernameInvalid("Username");
                        }
                    }

                    boolean boolNewPassword = false;
                    while (!boolNewPassword) {
                        System.out.print("Enter New Password: ");
                        newPassword = sc.nextLine();

                        if (newPassword.matches(".*[a-zA-Z].*") && newPassword.matches(".*[0-9].*") && newPassword.length() >= 8) {
                            boolNewPassword = true;
                        } else {
                            methods.PasswordInvalid("Password");
                        }
                    }
                    System.out.println();
                    boolean boolBirthYear = false;
                    while (!boolBirthYear) {
                        System.out.print("Enter Year of Birth (YYYY): ");
                        try {
                            newYear = sc.nextInt();
                            if (newYear > 2023) {
                                methods.YearInvalid("Birth Year");
                            } else {
                                boolBirthYear = true;
                            }
                        } catch (InputMismatchException e) {
                            methods.NumericalError("Birth Month");
                            sc.nextLine();
                        }
                    }
                    boolean boolBirthMonth = false;
                    while (!boolBirthMonth) {
                        System.out.print("Enter Month of Birth (MM): ");
                        try {
                            newMonth = sc.nextInt();
                            if (newMonth < 1 || newMonth > 12) {
                                methods.MonthInvalid("Birth Month");
                            } else {
                                boolBirthMonth = true;
                            }
                        } catch (InputMismatchException e) {
                            methods.NumericalError("Birth Month");
                            sc.nextLine();
                        }
                    }

                    boolean boolBirthDay = false;
                    while (!boolBirthDay) {
                        System.out.print("Enter Day of Birth (DD): ");
                        newDay = sc.nextInt();
                        if (newMonth == 2 && newDay > 29) {
                            methods.DayInvalid("Birth Day");
                        } else {
                            boolBirthDay = true;
                        }
                    }
                    Integer.toString(newYear); Integer.toString(newMonth); Integer.toString(newDay);
                    newBirthday = newYear + "-" + newMonth + "-" + newDay;

                    System.out.println();
                    System.out.println("+-----------Security Questions------------+");
                    System.out.println("| 1. In what city were you born?");
                    System.out.println("| 2. What is the name of your favorite pet?");
                    System.out.println("| 3. What high school did you attend?");
                    System.out.println("+-----------------------------------------+");
                    System.out.println(": This will be used for security purposes :");
                    System.out.println();
                    boolean boolSecuQuestion = false;
                    while (!boolSecuQuestion) {
                        System.out.print("Enter Question Number (1-3): ");
                        newSecuQuestion = sc2.nextLine();
                        switch (newSecuQuestion) {
                            case "1":
                                newSecuQuestion = "In what city were you born? ";
                                boolSecuQuestion = true;
                                break;
                            case "2":
                                newSecuQuestion = "What is the name of your favorite pet? ";
                                boolSecuQuestion = true;
                                break;
                            case "3":
                                newSecuQuestion = "What high school did you attend? ";
                                boolSecuQuestion = true;
                                break;
                            default:
                                System.out.println("Question Number ranges 1-3 only!");
                                break;
                        }
                    }
                    System.out.print(newSecuQuestion);
                    newSecuAnswer = sc2.nextLine();

                    String newAccountQuery = "INSERT INTO mini_twitter_accounts VALUES(\"" + 0 + "\", \"" + newUsername + "\", \"" + newPassword + "\", \"" + newBirthday + "\"," +
                            "\"" + newSecuQuestion + "\", \"" + newSecuAnswer + "\", \"" + newFollowers + "\", \"" + newFollowing + "\")";
                    st.executeUpdate(newAccountQuery);

                    boolLiOrSu = true;
                    methods.LoadingAnim("Creating Account");

                } else if (liOrSu.equalsIgnoreCase("li")) {
                    boolLiOrSu = true;
                } else {
                    methods.CodeInvalid("Log In or Sign Up");
                }
                int exisCounter = 0;
                while (rs.next()) {
                    exisID[exisCounter] = rs.getInt("account_id");
                    exisUsername[exisCounter] = rs.getString("account_username");
                    exisPassword[exisCounter] = rs.getString("account_password");
                    exisBirthday[exisCounter] = rs.getString("account_birthday");
                    exisSecuQuestion[exisCounter] = rs.getString("account_security_question");
                    exisSecuAnswer[exisCounter] = rs.getString("account_security_answer");
                    exisFollowers[exisCounter] = rs.getInt("account_followers");
                    exisFollowing[exisCounter] = rs.getInt("account_following");
                    exisCounter++;
                }

                boolean boolLogUser = false; boolean boolLogUserValidator = false;
                while (!boolLogUser) {
                    System.out.print("Enter Username: ");
                    logUsername = sc2.nextLine();

                    for (int i = 0; i < exisUsername.length; i++) {
                        if (logUsername.equals(exisUsername[i])) {
                            logId = exisID[i];
                            logBirthday = exisBirthday[i];
                            logSecuQuestion = exisSecuQuestion[i];
                            logSecuAnswer = exisSecuAnswer[i];
                            logPasswordValidator = exisPassword[i];
                            logFollowers = exisFollowers[i];
                            logFollowing = exisFollowing[i];
                            boolLogUserValidator = true;
                            break;
                        }
                    } if (boolLogUserValidator) {
                        boolLogUser = true;
                    } else {
                        System.out.println("Invalid Username!");
                        System.out.println();
                    }
                }
                boolean boolLogPass = false; boolean boolLogPassValidator = false;
                while (!boolLogPass) {
                    System.out.print("Enter Password: ");
                    logPassword = sc2.nextLine();

                    for (int g = 0; g < exisPassword.length; g++) {
                        if (logPassword.equals(exisPassword[g]) && logUsername.equals(exisUsername[g])) {
                            boolLogPassValidator = true;
                            break;
                        }
                    } if (boolLogPassValidator) {
                        boolLogPass = true;
                    } else {
                        System.out.println("Invalid Password!");
                        System.out.println();
                    }
                }

                methods.LoadingAnim("Logging In");

                System.out.println("          TwitterMini");
                System.out.println();
                System.out.println("@" + logUsername);
                System.out.println(logBirthday);
                System.out.print(logFollowing + " Following" + "   " + logFollowers + " Followers" );
                System.out.println();
                System.out.println("Connect with Friends!");
                while (rs.next()) {
                    System.out.println("User ID: " + rs.getInt("account_id"));
                    System.out.println("Username: @" + rs.getString("account_username"));
                    System.out.println();
                }
                System.out.println("Interact with users!");

                boolean boolSelectUser = false; boolean boolSelectUserValidator = false;
                while (!boolSelectUser) {
                    System.out.print("Enter User ID: ");
                    try {
                        int selectID = sc3.nextInt();
                        for (int i = 0; i < exisID.length; i++) {
                            if (selectID == exisID[i]) {
                                userId = exisID[i];
                                userName = exisUsername[i];
                                userBirthday = exisBirthday[i];
                                userFollowing = exisFollowing[i];
                                userFollowers = exisFollowers[i];
                                boolSelectUserValidator = true;
                                break;
                            }
                        }
                        if (boolSelectUserValidator) {
                            boolSelectUser = true;
                        } else {
                            System.out.println("User ID doesn't exist!");
                            System.out.println();
                        }
                    } catch (InputMismatchException e) {
                        methods.NumericalError("User ID");
                        sc3.nextLine();
                    }
                }
                String selectAccountName = userName + "_account";
                ResultSet rs2 = st.executeQuery("select * from " + selectAccountName);

                System.out.println("@" + userName);
                System.out.println(userBirthday);
                System.out.println(userFollowing + " Following" + "   " + userFollowers + " Followers" );
                System.out.println();
                System.out.println("        TWEETS");

                int z = 0;
                while (rs2.next()) {
                    userPostID[z] = rs.getInt("post_id");
                    userPost[z] = rs.getString("posts");
                    System.out.println("Tweet ID: " + rs.getInt("post_id"));
                    System.out.println(rs.getString("posts"));
                    System.out.println("Likes: " + rs.getInt("likes"));
                    System.out.println("Retweets: " + rs.getInt("retweets"));
                    System.out.println();
                }

                boolean boolEnterTweet = false; boolean boolEnterTweetValidator = false;
                while (!boolEnterTweet) {
                    System.out.print("Enter Tweet ID to Interact: ");
                    try {
                        interactTweet = sc3.nextInt();
                        for (int i = 0; i < userPostID.length; i++) {
                            if (interactTweet == userPostID[i]) {
                                interactPost = userPost[i];
                                interactLikes = userPostLikes[i];
                                interactRetweets = userPostRetweets[i];
                                boolEnterTweetValidator = true;
                                break;
                            }
                        } if (boolEnterTweetValidator) {
                            boolEnterTweet = true;
                        } else {
                            System.out.println("Tweet ID doesn't exist!");
                            System.out.println();
                        }
                    } catch (InputMismatchException e) {
                        methods.NumericalError("Tweet ID");
                        sc3.nextLine();
                    }
                }
                System.out.println();
                System.out.println("Tweet ID: " + interactTweet);
                System.out.println(interactPost);
                System.out.println();
                System.out.println("+------------------------+");
                System.out.println("|  CODE  |     OPTION    |");
                System.out.println("+------------------------+");
                System.out.println("|   LE   |      LIKE     |");
                System.out.println("|   RT   |     RETWEET   |");
                System.out.println("+------------------------+");

                boolean boolInteractCode = false;
                while (!boolInteractCode) {
                    System.out.println("Enter Code: ");
                    String interactCode = sc4.nextLine();

                    System.out.println();
                    if (interactCode.equalsIgnoreCase("le")) {
                        interactLikes++;
                        System.out.println("Tweet Liked!");
                        String likeQuery = "UPDATE " + selectAccountName + " SET likes = \"" + interactLikes + "\" + WHERE post_id = \"" + interactTweet + "\"";
                        st.executeUpdate(likeQuery);
                        boolInteractCode = true;
                    } else if (interactCode.equalsIgnoreCase("rt")) {
                        interactRetweets++;
                        System.out.println("Tweet Retweeted!");
                        String likeQuery = "UPDATE " + selectAccountName + " SET likes = \"" + interactRetweets + "\" + WHERE post_id = \"" + interactTweet + "\"";
                        st.executeUpdate(likeQuery);
                        boolInteractCode = true;
                    } else {
                        methods.CodeInvalid("Interact Code");
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error on SQL Connection!");
            e.printStackTrace();
        }
    }
}
