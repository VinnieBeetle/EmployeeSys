// Vincent Ho
//5/13/2022
//CMIS202-SecONL5 Spring 2022
//This project manages and saves employees
//information and allows to search through them
package com.example.employeesys;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;

import java.util.LinkedList;
import java.util.Stack;

public class EmployeeSys extends Application {

    //stage stuff
    Stage window;
    BorderPane layout1;
    Scene scene;
    Button save, search, prev, next, clockIn, clockOut;
    GridPane centerMenu, centerMenu1;
    Group root;
    //text fields for new employee
    TextField nameInput, ageInput, birthInput;
    TextField IDInput;
    //text field for searching an employee's name
    TextField searchNameInput;
    //File that holds employee information
    String filepath = "output.txt";
    //Table view
    TableView table;

    //decides on confirm box buttons - either "okay" or "Yes/No"
    int num;

    //For tableView - adding employees to it and what not
    ObservableList<Employee> oEmployees = FXCollections.observableArrayList();

    //For a creation of a stack
    Stack<String> employeeNames = new Stack<String>();

    //For LinkedList
    LinkedList linky = new LinkedList();

    //adding hashfunction
    HashFunction theFunc = new HashFunction(40); // it is set up in getEmployee()
    String[] elementsAdd = new String[40] ;
    String[] hashArray;

    //for a hardcoded binary tree
    TreeNode roots;

    //multithreadding?!?!
    Multithreading multithread = new Multithreading();



    public static void main(String[] args) {

        launch(args);
    }

    //stage stuff
    public void start(Stage stage) {

        //Creates the txt file if it doesn't exist
        try {
            FileWriter txtFile = new FileWriter("output.txt", true);
            txtFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //////////////////////////////////////////////////////////////
            //search test 22yr olds in console
            ArrayList<String> tests = reader(filepath,"22", 3);
             for(int i = 0; i < tests.size(); i++){
                System.out.println(tests.get(i));   //change this
             }
            //
            System.out.println();

        //hard coded binary tree
        createBinaryTree();

        //creating 5 threads that are counting up to 5
        for(int i = 0; i <5; i++) {
            Multithreading multithread = new Multithreading();
            multithread.start();
        }
        /////////////////////////////////////////////////////////////



        //Renaming the stage
        window = stage;



        //Menu bar start for "CRUDOX"
        Menu fileMenu = new Menu("_File");
            MenuItem exit = new MenuItem("Exit");
                exit.setOnAction(e -> {closeProgram(1);} );
            fileMenu.getItems().add(exit);
        Menu editMenu = new Menu("_Edit");
            MenuItem newEmployee = new MenuItem("Create A New Profile");
                editMenu.setOnAction(e-> {
                    layout1.setCenter(centerMenu);
                });
            editMenu.getItems().add(newEmployee);
        Menu helpMenu = new Menu("_Help");
            MenuItem about = new MenuItem("About");
                about.setOnAction(e -> {helpProgram(2);});
            helpMenu.getItems().add(about);
        Menu searchMenu = new Menu("_Search");
            MenuItem searchID = new MenuItem("ID");
                searchID.setOnAction(e -> {System.out.println("WIP SWITCH SCENE"); });
            //searchMenu.getItems().add(searchID);

            MenuItem searchName = new MenuItem("Name");
                searchName.setOnAction(e -> {
                    layout1.setCenter(centerMenu1);
                });
            searchMenu.getItems().add(searchName);
            MenuItem searchAge = new MenuItem("Age");
                searchAge.setOnAction(e -> {System.out.println("WIP SWITCH SCENE");});

        //MenuBar
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(fileMenu, editMenu, helpMenu, searchMenu);

        //Buttons
        save = new Button("Save");
        save.setOnAction(e->{newEmployee();});


        search = new Button("Search");
        save.setOnAction(e->{  ;});
        /* these are preps for the other buttons
        save = new Button("Save");
        save.setOnAction(e->{System.out.println("WIP SAVE");});

        save = new Button("Save");
        save.setOnAction(e->{System.out.println("WIP SAVE");});
        */

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




        //testing the Stacks
        System.out.println(employeeNames);
        System.out.println();

        //testing LinkedList
        System.out.println(linky);
        System.out.println(linky.getFirst());

        //tsting elementsAdd
        System.out.println(elementsAdd[1]);

        //Using  of a hash table
            /// ????
        //theFunc.hashFunction1(elementsAdd, theFunc.hashArray);






        // layout menu for inserting a new employee
        centerMenu = new GridPane();
        centerMenu.setPadding(new Insets(20,20,20,20));
        centerMenu.setVgap(8);
        centerMenu.setHgap(10);


        //labels to go along with creating a new employee
        Label name = new Label("Name");
        GridPane.setConstraints(name, 0,1);

        Label c = new Label("Age");
        GridPane.setConstraints(c, 0,2);
        Label d = new Label("Birthday");
        GridPane.setConstraints(d, 0,3);
        Label e = new Label("ID");
        GridPane.setConstraints(e, 0,4);

        //text fields for employee
        nameInput = new TextField();
        GridPane.setConstraints(nameInput, 1, 1);
        ageInput = new TextField();
        GridPane.setConstraints(ageInput, 1, 2);
        birthInput = new TextField();
        GridPane.setConstraints(birthInput, 1, 3);
        IDInput = new TextField();
        GridPane.setConstraints(IDInput, 1, 4);


        GridPane.setConstraints(save, 0, 8);

        centerMenu.getChildren().addAll( name, c, d, e, nameInput, ageInput, birthInput, IDInput, save);

        //layout for searching for an ID
        centerMenu1 = new GridPane();
        centerMenu1.setPadding(new Insets(20,20,20,20));
        centerMenu1.setVgap(8);
        centerMenu1.setHgap(10);

        Label fullName = new Label("Name");
        GridPane.setConstraints(fullName, 0,1);

        searchNameInput = new TextField();
        GridPane.setConstraints(searchNameInput, 1, 1);

        GridPane.setConstraints(search, 0,2);

        centerMenu1.getChildren().addAll(fullName, searchNameInput, search);



        //layout menu for everything
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
        ConfirmBox.display("About",Help.about(), num);
    }
    //display save confirmation popup
    public void savedProgram(int num){
        ConfirmBox.display("Saving", "You have saved!", num);
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
    //and includes a stack of their names
    public ObservableList<Employee> getEmployee(){

        String currentLine;
        String[] data;

        //hashtable space
        int i = 1;
        //String to be inserted for hashtable
        String string;
        int j = 0;

            try{
                FileReader fr = new FileReader(filepath);
                BufferedReader br = new BufferedReader(fr);


                    while((currentLine = br.readLine()) != null){

                        data = currentLine.split(",");
                        oEmployees.add(new Employee(data[0],data[1],data[2],data[3],data[4],data[5]));
                        employeeNames.add(data[1]);
                        linky.add(data[1]);

                        //adding up to 30 some elements into hash array
                        if (j <31) {
                            elementsAdd[j] = data[1];
                            j++;
                        }

                }

                br.close();
                fr.close();


            }
            catch(Exception e){
                System.out.println(e);
            }


        return oEmployees;

    }
    //once I add the add page and text field for them thingies I'll connect them to add into the text file and update
    public void newEmployee(){

        //it updates the table too, checker is used to make sure that all the textfields are filled out
        if(checker()) {
            try {
                FileWriter fw = new FileWriter(filepath, true);
                BufferedWriter bw = new BufferedWriter(fw);


                //All the stuff is getting replaced with getters
                oEmployees.add(new Employee(IDInput.getText(), nameInput.getText(), ageInput.getText(), birthInput.getText(), "sec", "sec"));

                bw.write("\n" + IDInput.getText() + "," + nameInput.getText() + "," + ageInput.getText() + "," + birthInput.getText() + "," + "sec," + "sec");

                bw.close();
                fw.close();


            } catch (Exception e) {
                System.out.println(e);
            }
            layout1.setCenter(table);
            savedProgram(2);
        }else {
            ConfirmBox.display("Error", "There seems to be empty texts", 2);
        }
    }

    public boolean checker(){
        boolean check = true;

        if(
                IDInput.getText().trim().isEmpty()
                ||nameInput.getText().trim().isEmpty()
                ||ageInput.getText().trim().isEmpty()
                ||birthInput.getText().trim().isEmpty()



        ){
            check = false;

        }
          return check;
    }

    //basic node for binary tree
    public class TreeNode {
        int data;
        TreeNode left;
        TreeNode right;

        public TreeNode(int data){
            this.data = data;
        }
    }
    //make binary tree
    public void createBinaryTree(){
        TreeNode first = new TreeNode(1);
        TreeNode second = new TreeNode(2);
        TreeNode third = new TreeNode(3);
        TreeNode fourth = new TreeNode(4);
        TreeNode fifth = new TreeNode(5);

        roots = first;
        first.left = second;
        first.right = third;

        second.left = fourth;
        second.right = fifth;

    }


    //generic array retriever
    public static <T> void displayArray(T[] array){

        for(T x : array){
            System.out.print(x + " ");
        }
        System.out.println();
    }

}