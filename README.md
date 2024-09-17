# Power Grid Optimization and ESV Deployment Project

This project consists of two missions: **Power Grid Optimization** and **Eco-Maintenance**. The project utilizes dynamic programming and greedy algorithms to solve these two important energy management problems.

## Project Structure

The project is divided into the following classes:

- **Main**: The main class that ties both missions together and processes input/output.
- **PowerGridOptimization**: Solves the Power Grid Optimization problem using dynamic programming.
- **OptimalPowerGridSolution**: Stores the optimal solution for the Power Grid Optimization mission.
- **OptimalESVDeploymentGP**: Solves the Eco-Maintenance problem using a greedy approach.
  
## Mission Descriptions

### 1. Mission Power Grid Optimization

The goal of this mission is to optimize the operation of a power grid, which receives a certain amount of energy demand each hour. You are tasked with determining the optimal hours to discharge the battery bank for maximum efficiency. The objective is to maximize the number of satisfied energy demands while considering the constraints of the power grid's capacity.

- The solution is implemented using **dynamic programming**.
- The program reads the hourly energy demands from a file and determines the best time slots to discharge the battery to meet the demands.
  
#### Input:

- A file that contains the energy demands arriving per hour in a space-separated format.

#### Output:

- The total number of demanded gigawatts.
- The maximum number of satisfied gigawatts.
- The hours at which the battery should be discharged for maximum efficiency.
- The number of unsatisfied gigawatts.

#### Example:

```
The total number of demanded gigawatts: 500
Maximum number of satisfied gigawatts: 400
Hours at which the battery bank should be discharged: 1, 3, 6, 8
The number of unsatisfied gigawatts: 100
```

### 2. Mission Eco-Maintenance

In this mission, we aim to optimize the deployment of Electric Service Vehicles (ESVs) for maintenance tasks. Each ESV has a limited capacity, and maintenance tasks have varying energy demands. The objective is to determine the minimum number of ESVs required to satisfy all tasks.

- The solution is based on the **First-Fit Decreasing** algorithm, a greedy approach.
- The program reads the number of available ESVs, the maximum capacity of each ESV, and the energy demands of the maintenance tasks from a file.
  
#### Input:

- A file containing two lines:
  - First line: The number of available ESVs and their maximum capacity.
  - Second line: The energy demands of the maintenance tasks.

#### Output:

- The minimum number of ESVs required to deploy.
- The tasks assigned to each ESV.

#### Example:

```
The minimum number of ESVs to deploy: 4
ESV 1 tasks: [100]
ESV 2 tasks: [80, 20]
ESV 3 tasks: [70, 30]
ESV 4 tasks: [50, 40, 10]
```

## How to Run

1. Compile the Java project:
   ```
   javac Main.java PowerGridOptimization.java OptimalPowerGridSolution.java OptimalESVDeploymentGP.java
   ```

2. Run the project with the appropriate input files:
   ```
   java Main demandSchedule.dat ESVMaintenance.dat
   ```

3. Ensure that the `demandSchedule.dat` and `ESVMaintenance.dat` files are in the correct format:
   - `demandSchedule.dat` should contain space-separated hourly energy demands.
   - `ESVMaintenance.dat` should have two lines:
     - The first line contains the number of available ESVs and their maximum capacity.
     - The second line contains space-separated energy demands for the tasks.

## Example Input Files

### `demandSchedule.dat`

```
50 60 70 80 90 100 110 120
```

### `ESVMaintenance.dat`

```
5 100
20 50 40 70 10 30 80 100 10
```
