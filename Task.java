import java.util.ArrayList;
import java.util.Scanner; 
import java.time.LocalDate;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
public class Task{
    private int id; 
    private String description; 
    private String status; 
    private LocalDate createdAt;
    private LocalDate updatedAt;

    //constructor
    public Task(int id, String description, String status){
        this.id = id;
        this.description = description;
        //starts in false so the tracker doesnt automatically say completed 
        this.status = status;
    }

    //setters
    public void setDesc(String description){
        this.description = description;
    }

    public void setStatus(String status){
        this.status = status;
    }

    public void setUpdatedAt(){

    }

    //getters
    public int getId(){
        return this.id;
    }

    public String getDesc(){
        return this.description;
    }

    public String getStatus(){
        return this.status;
    }
    
    public static void addTask(ArrayList<Task> tasks, Scanner scanner){
        System.out.print("Enter task Description: ");
        
        String desc = scanner.nextLine();

        String stat = "in-progress";
        int id = tasks.size() + 1;        

        tasks.add(new Task(id, desc, stat));
        System.out.println("Task Successfully Added!");
        System.out.println(id + " " + stat);
    }

    public static void updateTask(ArrayList<Task> tasks, Scanner scanner){

        if(tasks.isEmpty()){
            System.out.println("There are no tasks to update.\n");
        } else {
            //show the current task
            System.out.println("Current Tasks");
            for (int i = 0; i < tasks.size(); i++){
            //tasks.get(i) > opens the tasks and finds the i + then inside we get the description with .getDesc()
            System.out.println(tasks.get(i).getId() + " : " + tasks.get(i).getDesc());
            }
        
            System.out.print("Which Task do you want to update?: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            
            for (Task task : tasks){
                if(task.getId() == id){

                //name of the task
                System.out.print("What is the new name of this task?: ");
                String d = scanner.nextLine();
                task.setDesc(d);
                
                //swith statement
                System.out.print("What is the current Status: ");
                System.out.print("1: Done, 2: In-Progress, 3: To-do : ");

                int option = scanner.nextInt();
                switch (option){
                    case 1:
                    task.setStatus("Done");
                    break;
                    case 2: 
                    task.setStatus("In-Progress");
                    break;
                    case 3:
                    task.setStatus("To-Do");
                    break; 
                    default: 
                    System.out.println("Thats not an option.");
                    break;
                }
            
                System.out.println("Task successfully updated!");
                break; // Stops looking once it finds the match
                }
            }
        } 

    }
    //loops through the array and displays whats in it.
    public static void listTasks(ArrayList<Task> tasks){
        if (tasks.isEmpty()) {   
            System.out.println("There are no tasks to update.");
        } else {
            for (Task task : tasks){
                System.out.println(task.id + ": " + task.description + " " + task.status);
            }    
        }
    }

    public static void filterTask(ArrayList<Task> tasks, String status){
    //to filter get one list make a copy of that list only taking the items with completed in them.
        ArrayList<Task> filteredTasks = tasks.stream() 
            .filter(task -> task.getStatus().equalsIgnoreCase(status))
            .collect(Collectors.toCollection(ArrayList::new));

            if(filteredTasks.isEmpty()){
                System.out.println("No tasks with the status: " + status);
            } else {
                for (Task task : filteredTasks){
                    System.out.println(task.getId() + ": " + task.getDesc() + " [" + task.getStatus() + "]");
                }
            }
    }

    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<>();
        
        tasks.add(new Task(0, "Boo", "In-Progress"));
        tasks.add(new Task(1, "BooBoo", "In-Progress"));
        tasks.add(new Task(2, "BooBooBoo", "In-Progress"));

        try(Scanner s = new Scanner(System.in)){
            boolean running = true;
            int count = 0;

            do {
            
            if (count > 0){
                System.out.println("\nWould you like to do something else?");
            } else {
                System.out.println("Welcome, what would you like to do?");
                
            }

            count ++;

            String options = """
                    1: Add
                    2: Update
                    3: List All Tasks
                    4: List Finished Tasks
                    5: List Tasks in Progress
                    6: List Tasks To-Do
                    """;

            System.out.println(options);
            System.out.print("Enter choice: ");

            if (s.hasNextInt()) {
                int option = s.nextInt();
                s.nextLine(); 

                switch(option){
                    case 1: 
                    addTask(tasks, s);
                    break; 
                    case 2: 
                    updateTask(tasks, s);
                    break;
                    case 3: 
                    listTasks(tasks);
                    break;
                    case 4:
                    filterTask(tasks, "Done");
                    break;
                    case 5:
                    filterTask(tasks, "In-Progress");
                    break;
                    case 6: 
                    filterTask(tasks, "To-Do");
                    break;
                    default:
                    System.out.println("Not a correct choice.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                s.nextLine(); // Clear the invalid input
            }
            } while(running);
        }
    }
}

