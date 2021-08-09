import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
//-----------------------------------------------
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Stream;

public class TrainBookingSystem extends Application {
    /**
     * @author Sakun Edirisinghe (W1761882)
     *
     */
    public static String[][] Seats= new String[42][2];
    public static int[] findName(String[][]arr, String t) {
        //using liner search in string...
        int len = arr.length;
        int count=0;

        for (int i =0; i < len;i++) {

            try {
                if (arr[i][1].equals(t)) {
                    count+=1;
                } else {        //section for count the seats
                    continue;
                }
            } catch (NullPointerException e){
                continue;
            }
        }
        int[] seat= new int[count];
        int y=0;
        for (int i =0; i < len;i++) {
            try {
                if (arr[i][1].equals(t)) {
                    seat[y]=i+1;        // add seats' number to array
                    y+=1;
                } else {
                    continue;
                }
            } catch (NullPointerException e){
                continue;
            }
        }
        return seat;
    }
    public static String[] bubbleSort(String[] arr)
    {
        int n = arr.length;
        for (int i=0; i<n;i++){
            if (arr[i]==null){           //bubble sorting in String
                arr[i]="";
            }
        }
        for (int i = 0; i < n-1; i++)
            for (int j = 0; j < n-i-1; j++)
                if (0 > arr[j+1].compareTo(arr[j]))
                {
                    // swap arr[j+1] and arr[i]
                    String temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
        return arr;
    }

    /* Print the arrays' elements */
    public static void printArray(HashSet<String> arr)
    {
        for (String i : arr){
            System.out.println(i);
        }
    }
    private static void programm(String File) throws IOException {
        Scanner sc=new Scanner(System.in);
        begin:
        for (;;){
            System.out.println("Please enter Date following date type correctly");
            System.out.println("When you enter today date just press the \"Enter\"");
            System.out.println("Exit the program enter \"Q\"");
            System.out.println("yyyy-MM-dd");
            String date = sc.nextLine();        //date comparing section...
            Date date2 = new Date();
            Date date1 = null;
            if (date.equals("q") || date.equals("Q")){
                break begin;
            } else if (date.equals("")) {
                date1 = new Date();
            } else {
                try {
                    date1 =new SimpleDateFormat("yyyy-MM-dd").parse(date);
                } catch (Exception e) {
                    System.out.println("Enter Date correctly!!!");
                    continue;
                }
            }
            System.out.println("Selected date : " + date1);
            System.out.println("Today : " + date2);
            if (date1.after(date2)||date1.equals(date2)) {
                Menu:
                for (;;) {
                    System.out.println("Enter \"A\" to add a customer ");
                    System.out.println("Enter \"V\" to view all the seat ");
                    System.out.println("Enter \"E\" to view empty seats ");
                    System.out.println("Enter \"D\" to delete a booked seats ");
                    System.out.println("Enter \"F\" to find a seat by customer's ID ");
                    System.out.println("Enter \"S\" Save Data ");
                    System.out.println("Enter \"L\" Load Data ");
                    System.out.println("Enter \"O\" Oder Customer's name  ");
                    System.out.println("Enter \"Q\" to quit ");
                    System.out.print("Enter character :-");
                    String option=sc.next();
                    switch(option){
                        case "A":
                        case "a":
                            addCustomer();
                            break;
                        case "V":
                        case "v":
                            viewAllSeats();
                            break;
                        case "E":
                        case "e":
                            viewEmptySeats();
                            break;
                        case "D":
                        case "d":
                            deleteBooking();
                            break;
                        case "F":
                        case "f":
                            finalSeatByCustomer();
                            break;
                        case "O":
                        case "o":
                            bubblesorting();
                            break;
                        case "S":
                        case "s":
                            Saving(date1,Seats,File);
                            break;
                        case "L":
                        case "l":
                            Loading(date1,Seats,File);
                            break;
                        case "Q":
                        case "q":
                            for (int i=0; i<Seats.length; i++){
                                Seats[i][0]=null;
                                Seats[i][1]=null;
                            }
                            break begin;
                        default:
                            System.out.println("Invalid Input");

                    }
                }
            }else if (date1.before(date2)) {
                Menu:
                for (;;) {
                    System.out.println("Enter \"A\" to add a customer ");
                    System.out.println("Enter \"V\" to view all the seat ");
                    System.out.println("Enter \"E\" to view empty seats ");
                    System.out.println("Enter \"D\" to delete a booked seats ");
                    System.out.println("Enter \"F\" to find a seat by customer's ID ");
                    System.out.println("Enter \"S\" Save Data ");
                    System.out.println("Enter \"L\" Load Data ");
                    System.out.println("Enter \"O\" Order Customer's name  ");
                    System.out.println("Enter \"Q\" to quit ");
                    System.out.print("Enter character :-");
                    String option=sc.next();
                    switch(option){
                        case "A":
                        case "a":
                            System.out.println("This function is disabled due to enter past date");
                            break;
                        case "V":
                        case "v":
                            viewAllSeats();
                            break;
                        case "E":
                        case "e":
                            viewEmptySeats();
                            break;
                        case "D":
                        case "d":
                            System.out.println("This function is disabled due to enter past date");
                            break;
                        case "F":
                        case "f":
                            finalSeatByCustomer();
                            break;
                        case "O":
                        case "o":
                            bubblesorting();
                            break;
                        case "S":
                        case "s":
                            System.out.println("This function is disabled due to enter past date");
                            break;
                        case "L":
                        case "l":
                            Loading(date1,Seats,File);
                            break;
                        case "Q":
                        case "q":
                            break Menu;
                        default:
                            System.out.println("Invalid Input");

                    }
                }
            }
        }
    }
    //---------------------- program function Methods----------------------------------------
    private static void Loading(Date date1,String[][] arr,String File) throws IOException {
        try {
            File myObj = new File(date1+File+".txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                for (int i = 0; i < arr.length; i++) {
                    for (int x = 0; x < 2; x++) {
                        arr[i][x]=myReader.nextLine();      //read elements in line by line
                    }
                }
            }
            myReader.close();
            System.out.println("Loading complete!!");
            System.out.println();
            System.out.println();
        } catch (FileNotFoundException e) {
            System.out.println("Loading error!");       //file not found
            e.printStackTrace();
            System.out.println();
            System.out.println();
        }
    }

    private static void Saving(Date date1,String[][] arr,String File) throws IOException {
        //file handling for saving
        FileWriter file = new FileWriter(date1+File+".txt");
        for (int i = 0; i < arr.length; i++) {
            for (int x = 0; x < 2; x++) {
                file.write(arr[i][x]+"\n");     // write elements in line by line
            }
        }
        file.close();
        System.out.println("Saving Complete");
        System.out.println();
        System.out.println();
    }

    private static void bubblesorting() {
        //create duplicate array
        String[] Duplicate_Seats=new String[42];
        for (int i=0; Seats.length>i; i++)
            Duplicate_Seats[i]=Seats[i][0];
        bubbleSort(Duplicate_Seats);
        System.out.println("Sorted List");
        HashSet<String> set=new HashSet<String>();
        for (int i=0; Seats.length>i; i++)
            if (set.add(Duplicate_Seats[i])){
                continue;
            } else {
                continue;
            }
        printArray(set);
        System.out.println();
        System.out.println();
    }

    private static void finalSeatByCustomer() {
        Scanner sc= new Scanner(System.in);
        System.out.print("Enter Customer ID:-");
        String CustName = sc.nextLine();
        System.out.println(Arrays.toString(findName(Seats,CustName)));
        System.out.println();
        System.out.println();
    }

    private static void deleteBooking() {
        Scanner sc= new Scanner(System.in);
        System.out.print("Enter Customer ID:-");
        String CustName = sc.nextLine();
        System.out.println(Arrays.toString(findName(Seats,CustName)));
        int len = findName(Seats,CustName).length;
        delete:
        for (int i=0;i<len;i++){
            System.out.print("Which seat you want remove:-");
            int SeatNo = sc.nextInt();
            moreseat:
            for (int x=0;x<len;x++){
                if (findName(Seats,CustName).length==0){
                    break;
                } else {
                    if (SeatNo == findName(Seats, CustName)[0]){
                        Seats[SeatNo-1][0]=null;
                        Seats[SeatNo-1][1]=null;
                        Scanner sc3= new Scanner(System.in);
                        System.out.print("You want to remove more seats? :-");
                        String Decision = sc3.nextLine();
                        switch (Decision){
                            case "Yes":
                            case "yes":
                                break moreseat;
                            case "No":
                            case "no":
                                break delete;
                            default:
                                System.out.println("Invalid Input");
                        }
                    } else {
                        continue;
                    }
                }
            }
        }
    }
    private static void viewEmptySeats() {
        Stage primaryStage = new Stage();
        primaryStage.setTitle("View Empty Seats");

        // create a FlowPane
        FlowPane flow_pane = new FlowPane(Orientation.VERTICAL,
                20.0, 20.0);
        //add labels
        Label[] array= new Label[42];
        for (int i = 0; i < 42; i++) {
            // add nodes to the flow pane
            Label labels=new Label("No. "+(i+1));
            array[i]=labels;
        }
        Button button43 = new Button("  OK   ");
        button43.setOnAction((event) -> {
            primaryStage.close();
        });
        for (int i=0; i<42;i++) {
            if (Seats[i][0] != null) {
                array[i].setVisible(false);
            }
        }
        flow_pane.setAlignment(Pos.CENTER);
        flow_pane.setColumnHalignment(HPos.CENTER);
        flow_pane.setRowValignment(VPos.CENTER);
        flow_pane.getChildren().addAll(array);
        flow_pane.getChildren().addAll(button43);
        // create a scene
        Scene scene = new Scene(flow_pane, 700, 400);

        // set the scene
        primaryStage.setScene(scene);

        primaryStage.showAndWait();
    }
    private static void viewAllSeats() {
        Stage primaryStage = new Stage();
        primaryStage.setTitle("View All Seats");

        // create a FlowPane
        FlowPane flow_pane = new FlowPane(Orientation.VERTICAL,
                20.0, 20.0);

        // add buttons
        Label[] array= new Label[42];
        for (int i = 0; i < 42; i++) {
            // add nodes to the flow pane
            Label labels=new Label("No. "+(i+1));
            array[i]=labels;
        }
        Button button43 = new Button("  OK   ");
        button43.setOnAction((event) -> {
            primaryStage.close();
        });
        flow_pane.setAlignment(Pos.CENTER);
        flow_pane.setColumnHalignment(HPos.CENTER);
        flow_pane.setRowValignment(VPos.CENTER);
        flow_pane.getChildren().addAll(array);
        flow_pane.getChildren().addAll(button43);
        // create a scene
        Scene scene = new Scene(flow_pane, 700, 400);

        // set the scene
        primaryStage.setScene(scene);

        primaryStage.showAndWait();
    }
    private static void addCustomer() {
        String[] Seats_by_id = new String[42];
        for (int i=0;i<42;i++){
            Seats_by_id[i]=Seats[i][1];
        }
        String name;
        String id;
        Stream<String> seats = Arrays.stream(Seats_by_id);
        for (;;){
            Scanner sc=new Scanner(System.in);
            System.out.print("Enter Customer's Name:-");
            name=sc.next();System.out.print("Enter Customer's ID:-");
            id=sc.next();
            String finalId = id;
            try {
                if (seats.anyMatch(ids -> ids.contains(finalId))){
                    System.out.println("Already added");
                    continue;
                } else {
                    break;
                }
            } catch (Exception e) {
                System.out.print("NullPointerException Caught");
                break;
            }
        }
        //create temporary list for assigned value from buttons to array
        ArrayList<Integer> tempList= new ArrayList<>();
        //Gui part for booking seats
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Booking Seat");
        // create a FlowPane
        FlowPane flow_pane = new FlowPane(Orientation.VERTICAL,
                20.0, 20.0);
        // add buttons
        Button[] array = new Button[42];
        for (int i = 0; i < 42; i++) {
            // add nodes to the flow pane
            Button button = new Button("No. "+(i+1));
            array[i]=button;
            int finalI = i;
            button.setOnAction(event -> {
                button.setStyle("-fx-background-color:#ff0000");
                tempList.add(finalI);
            });
        }//checking button value
        for (int i=0; i<42;i++) {
            if (Seats[i][0] != null) {
                array[i].setStyle("-fx-background-color:red;");
                array[i].setMouseTransparent(true);
            }
        }

        flow_pane.setAlignment(Pos.CENTER);
        flow_pane.setColumnHalignment(HPos.CENTER);     //Button alignment
        flow_pane.setRowValignment(VPos.CENTER);
        flow_pane.getChildren().addAll(array);

        Button button43 = new Button("  OK   ");
        button43.setLayoutX(310);
        button43.setLayoutY(380);                       //ok button section(add customer)
        String finalName = name;
        String finalId1 = id;
        button43.setOnAction((event) -> {
            for (int i=0;i<tempList.size();i++){
                Seats[tempList.get(i)][0]= finalName;
                Seats[tempList.get(i)][1]= finalId1;
            }
            primaryStage.close();
        });
        Button button44 = new Button("Cancel");
        button44.setLayoutX(370);
        button44.setLayoutY(380);
        button44.setOnAction((event) -> {               // cancel button section(add customer)
            tempList.clear();
            primaryStage.close();
        });
        flow_pane.getChildren().addAll(button43,button44);
        // create a scene
        Scene scene = new Scene(flow_pane, 700, 400);

        // set the scene
        primaryStage.setScene(scene);

        primaryStage.showAndWait();
    }
    @Override
    public void start(Stage primaryStage) throws Exception{
        start:
        for (;;){
            System.out.println("Colombo-Badulla Enter \"1\"");
            System.out.println("Badulla-Colombo Enter \"2\"");
            System.out.println("Exit press \"Q\"");
            System.out.print("Choose route option:-");          //program running section!
            Scanner sc = new Scanner(System.in);
            String route = sc.next();
            switch (route) {
                case "1":
                    String Filename = "CB";
                    programm(Filename);
                    break;
                case "2":
                    Filename = "BC";
                    programm(Filename);
                    break;
                case "Q":
                case "q":
                    break start;
                default:
                    System.out.println("Invalid Input");
            }
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
}








