package com.example.employeesys;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;

public class EmployeeSys extends Application {

    //stage stuff
    Stage window;
    BorderPane layout1;
    Scene scene;
    Button save, prev, next, clockIn, clockOut;
    GridPane centerMenu;
    Group root;
    //text fields
    TextField nameInput, ageInput, birthInput;
    TextField IDInput;
    //File that holds employee information
    String filepath = "output.txt";
    //Table view
    TableView table;

    //decides on confirm box buttons - either "okay" or "Yes/No"
    int num;

    //For tableView - adding employees to it and what not
    ObservableList<Employee> oEmployees = FXCollections.observableArrayList();

    public static void main(String[] args) {

        launch(args);
    }

    //stage stuff
    public void start(Stage stage) {
        //Creates the txt file if it doesnt exist
        try {
            FileWriter txtFile = new FileWriter("output.txt", true);
            txtFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //search test 22yr olds
        ArrayList<String> records = reader(filepath,"22", 3);

        for(int i = 0; i < records.size(); i++){
            System.out.println(records.get(i));   //change this
        }

        //Renaming the stage
        window = stage;



        //Menu bar start for CRUDOX
        Menu fileMenu = new Menu("_File");
            MenuItem exit = new MenuItem("Exit");
                exit.setOnAction(e -> {closeProgram(1);} );
            fileMenu.getItems().add(exit);
        Menu editMenu = new Menu("_Edit");
            MenuItem newEmployee = new MenuItem("Create A New Profile");
                editMenu.setOnAction(e-> {newEmployee();});
            editMenu.getItems().add(newEmployee);
        Menu helpMenu = new Menu("_Help");
            MenuItem about = new MenuItem("About");
                about.setOnAction(e -> {helpProgram(2);});
            helpMenu.getItems().add(about);
        Menu searchMenu = new Menu("_Search");
            MenuItem searchID = new MenuItem("ID");
                searchID.setOnAction(e -> {System.out.println("WIP SWITCH SCENE");});
            MenuItem searchName = new MenuItem("Name");
                searchName.setOnAction(e -> {System.out.println("WIP SWITCH SCENE");});
            MenuItem searchAge = new MenuItem("Age");
                searchAge.setOnAction(e -> {System.out.println("WIP SWITCH SCENE");});

        //MenuBar
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(fileMenu, editMenu, helpMenu);

        //Buttons
        save = new Button("Save");
        save.setOnAction(e->{System.out.println("WIP SAVE");});

        save = new Button("Save");
        save.setOnAction(e->{System.out.println("WIP SAVE");});

        save = new Button("Save");
        save.setOnAction(e->{System.out.println("WIP SAVE");});

        save = new Button("Save");
        save.setOnAction(e->{System.out.println("WIP SAVE");});

        //For table - change to fit to size? maybe
        //Name column
        TableColumn<Employee, Integer> IDColumn = new TableColumn<>("ID");
        IDColumn.setMinWidth(200);
        IDColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));

        TableColumn<Employee, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(200);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Employee, Integer> ageColumn = new TableColumn<>("Age");
        ageColumn.setMinWidth(100);
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));

        TableColumn<Employee, String> birthColumn = new TableColumn<>("Birth");
        birthColumn.setMinWidth(200);
        birthColumn.setCellValueFactory(new PropertyValueFactory<>("birth"));

        table = new TableView<>();
        table.setItems(getEmployee());
        table.getColumns().addAll(IDColumn, nameColumn, ageColumn, birthColumn);

        //text fields for employee
        nameInput = new TextField();
        ageInput = new TextField();
        birthInput = new TextField();
        IDInput = new TextField();

        //to load up files


        layout1 = new BorderPane();
        layout1.setTop(menuBar);
        layout1.setCenter(table);


        scene = new Scene(layout1, 600,500);
        window.setTitle("System");
        window.setScene(scene);
        window.show();

    }
    //display about project popup

    public void helpProgram(int num){
        Boolean answer = ConfirmBox.display("About",Help.about(), num);
    }
    //display save confirmation popup
    public void savedProgram(int num){
        Boolean answer = ConfirmBox.display("Saving", "You have saved!", num);
    }
    // double check if they want to exit popup
    public void closeProgram(int num){
        Boolean answer = ConfirmBox.display("You Are Trying To Exit", "are you sure you want to exit?",num);
        if(answer){
            System.out.println("info was saved");
            window.close();

        }
    }
    //double check if you want to delete user popup
    public boolean deleteProgram(int num){
        Boolean answer = ConfirmBox.display("Deleting", "are you sure you want to delete this user?",num);

        return answer;
    }

    //quicksort ID into order I guess
    public static void quickSort (int[] array, int start, int end){

        if(end <= start) return; //base case

        int pivot = partition(array, start, end);
        quickSort(array, start, pivot-1);
        quickSort(array, pivot+1, end);

    }
    //Assists quickSort
    public static int partition(int[] array, int start, int end){

        int pivot = array[end];
        int i = start -1;

        for(int j = start; j <=end -1; j++){
            if(array[j] < pivot){
                i++;
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        i++;
        int temp = array[i];
        array[i] = array[end];
        array[end] = temp;


        return i;
    }


    //this is activated through Search - returns a string
    public static ArrayList<String> reader(String file, String searchTerm, int termPos){

        int pos = termPos - 1;

        String currentLine;
        String[] data;
        ArrayList<String> returnData = new ArrayList<>();

        try{
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            while((currentLine = br.readLine()) != null){

                data = currentLine.split(",");
                if((data[pos].equals(searchTerm))){
                    returnData.add(currentLine);
                }
            }

            br.close();
            fr.close();

            return returnData;

        }
        catch(Exception e){
            System.out.println(e);
        }
        return returnData;

    }
    //assists in reader, finds key term
    public String search(String searched){

        String toString = "";
        ArrayList<String> records = reader(filepath,searched, 2);

        for(int i = 0; i < records.size(); i++){
            toString = toString + records.get(i) +"\n";   //change this
        }
        return toString;
    }

    //for table view
    public ObservableList<Employee> getEmployee(){

        String currentLine;
        String[] data;

            try{
                FileReader fr = new FileReader(filepath);
                BufferedReader br = new BufferedReader(fr);

                while((currentLine = br.readLine()) != null){

                    data = currentLine.split(",");
                    oEmployees.add(new Employee(data[0],data[1],data[2],data[3],data[4],data[5]));


                }

                br.close();
                fr.close();


            }
            catch(Exception e){
                System.out.println(e);
            }

        return oEmployees;

    }
    //once I add the add page and textfield for them thingys I'll connect them to add into the text file and update
    public void newEmployee(){
        Employee employee = new Employee();
        ;

        try {
            FileWriter fw = new FileWriter(filepath, true);
            BufferedWriter bw = new BufferedWriter(fw);

            //All the stuff is getting replaced with getters
            oEmployees.add(new Employee("123123","sec","sec","sec","sec","sec"));

            bw.write("\n" + "123123," + "sec," + "sec," + "sec,"+ "sec,"+"sec");

            //oEmployees.add(new Employee(data[0],data[1],data[2],data[3],data[4],data[5]));

            bw.close();
            fw.close();

        }catch (Exception e){
            System.out.println(e);
        }

    }

    //generic array retriever
    public static <T> void displayArray(T[] array){

        for(T x : array){
            System.out.print(x + " ");
        }
        System.out.println();
    }

}