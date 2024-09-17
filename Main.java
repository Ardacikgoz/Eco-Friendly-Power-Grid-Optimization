import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Main class
 */
// FREE CODE HERE
public class Main {
    public static void main(String[] args) throws IOException {

       /** MISSION POWER GRID OPTIMIZATION BELOW **/

        System.out.println("##MISSION POWER GRID OPTIMIZATION##");
        // TODO: Your code goes here
        // You are expected to read the file given as the first command-line argument to read 
        // the energy demands arriving per hour. Then, use this data to instantiate a 
        // PowerGridOptimization object. You need to call getOptimalPowerGridSolutionDP() method
        // of your PowerGridOptimization object to get the solution, and finally print it to STDOUT.
        //List<String> demandScheduleInput = readFile("demandSchedule.dat",true,true);
        List<String> demandScheduleInput = readFile(args[0],true,true);

        String[] stringArray = demandScheduleInput.get(0).split(" ");
        ArrayList<Integer> arrayList = new ArrayList<>();
        int maxDemands = 0;
        for (int i = 0; i < stringArray.length; i++) {
            int val = Integer.parseInt(stringArray[i]);
            arrayList.add(val);
            maxDemands += val;
        }

        PowerGridOptimization optimization = new PowerGridOptimization(arrayList);
        OptimalPowerGridSolution opt = optimization.getOptimalPowerGridSolutionDP();
        System.out.println("The total number of demanded gigawatts: "+ maxDemands);
        System.out.println("Maximum number of satisfied gigawatts: "+ opt.maxNumberOfSatisfiedDemands);
        System.out.print("Hours at which the battery bank should be discharged: ");
        for(int i = 0; i < opt.getHoursToDischargeBatteriesForMaxEfficiency().size(); i++)
        {
            if(i != opt.getHoursToDischargeBatteriesForMaxEfficiency().size() - 1)
            {
                System.out.print(opt.getHoursToDischargeBatteriesForMaxEfficiency().get(i) + ", ");
            }
            else
                System.out.println(opt.getHoursToDischargeBatteriesForMaxEfficiency().get(i));
        }
        System.out.println("The number of unsatisfied gigawatts: " + (maxDemands - opt.getmaxNumberOfSatisfiedDemands()));

        System.out.println("##MISSION POWER GRID OPTIMIZATION COMPLETED##");


        /** MISSION ECO-MAINTENANCE BELOW **/

        System.out.println("##MISSION ECO-MAINTENANCE##");
        // TODO: Your code goes here
        // You are expected to read the file given as the second command-line argument to read
        // the number of available ESVs, the capacity of each available ESV, and the energy requirements 
        // of the maintenance tasks. Then, use this data to instantiate an OptimalESVDeploymentGP object.
        // You need to call getMinNumESVsToDeploy(int maxNumberOfAvailableESVs, int maxESVCapacity) method
        // of your OptimalESVDeploymentGP object to get the solution, and finally print it to STDOUT.

        //List<String> esvInput = readFile("ESVMaintenance.dat",true,true);
        List<String> esvInput = readFile(args[1],true,true);
        String[] firstLine = esvInput.get(0).split(" ");
        String[] secondLine = esvInput.get(1).split(" ");
        int totalVehicle = Integer.parseInt(firstLine[0]);
        int maxEnergy = Integer.parseInt(firstLine[1]);

        ArrayList<Integer> vehicles = new ArrayList<>();
        for(int i = 0 ; i< secondLine.length; i++)
        {
            vehicles.add(Integer.parseInt(secondLine[i]));
        }

        OptimalESVDeploymentGP optESV = new OptimalESVDeploymentGP(vehicles);
        int count = optESV.getMinNumESVsToDeploy(totalVehicle,maxEnergy);

        if(count == -1)
        {
            System.out.println("Warning: Mission Eco-Maintenance Failed.");
        }
        else {
            System.out.println("The minimum number of ESVs to deploy: "+count);
            for(int i = 0; i < count; i++)
            {
                System.out.println("ESV "+ (i+1)+" tasks: " + optESV.getMaintenanceTasksAssignedToESVs().get(i));
            }
        }
        System.out.print("##MISSION ECO-MAINTENANCE COMPLETED##");
    }
    public static List<String> readFile(String path, boolean discardEmptyLines, boolean trim) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(path)); //Gets the content of file to the list.
            if (discardEmptyLines) { //Removes the lines that are empty with respect to trim.
                lines.removeIf(line -> line.trim().equals(""));
            }
            if (trim) { //Trims each line.
                lines.replaceAll(String::trim);
            }
            return lines;
        } catch (IOException e) { //Returns null if there is no such a file.
            e.printStackTrace();
            return null;
        }
    }
}
