# Cron Expression Parser
### Project Description:
 a command line application which parses a cron string and expands each field
to show the times at which it will run.

## Getting Started

### Prerequisites
1. Java 8.x
2. Maven 3.x
3. IntelliJ IDEA

### Installation

1. Import Project in IntelliJ IDEA

   Click on **File** > **New** > **Project from Version Control** . Paste github repo url in URL and click on **Clone**
 
   OR
   
   Unzip the project and Click on **File** > **New** > **Project from Existing Sources** . Navigate to unzipped project location and click on **OK**


2. Installing dependencies

    Under Maven pane Click on **Lifecycle** > **clean**  and then **Lifecycle** > **install**


3. Build the project and run

   Navigate to **src/main/java/cronparser/Main.java** and right click on **main** method and click **Run 'Main.main()'**


3. Run tests

   Under Maven pane Click on **Lifecycle** > **test** 

### Usage

The cron string will be passed to  application as a single argument.

For example, the following input argument:
```
*/15 0 1,15 * 1-5 /usr/bin/find
```
Should yield the following output:
```
minute         0 15 30 45
hour           0
day of month   1 15
month          1 2 3 4 5 6 7 8 9 10 11 12
day of week    1 2 3 4 5
command        /usr/bin/find
```