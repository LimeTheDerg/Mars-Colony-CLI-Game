import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        System.out.println("The year is 20XX\nHumanity has created a colony on Mars, and you are the director\nType \"help\" for a brief tutorial\nGood luck");
        Scanner sc = new Scanner(System.in);
        int livingBuildingsOwned = 0;
        int miningBuildingsOwned = 0;
        int manufacturingBuildingsOwned = 0;
        int constructionBuildingsOwned = 3;
        int agriculturalBuildingsOwned = 0;
        int buildingsLimitToday = constructionBuildingsOwned;
        int jobless = 0;
        int stone = 100;
        int steel = 100;
        int silicon = 100;
        int components = 100;
        int food = 500;
        int componentsPerDay = 0;
        String textRequested = "";
        String[] argumentedCommand = {"","","",""};

        LivingBuildings livingSpacesRef = new LivingBuildings();
        MiningPlants miningPlantsRef = new MiningPlants();
        ManufacturingPlants manufacturingPlantsRef = new ManufacturingPlants();
        ConstructionOffices constructionOfficesRef = new ConstructionOffices();
        AgriculturalBuildings agriculturalBuildingsRef = new AgriculturalBuildings();



        while (!textRequested.equals("end game")) {
            textRequested = sc.nextLine().toLowerCase();

            if(textRequested.equals("make living space") && stone >= livingSpacesRef.stoneNeeded && steel >= livingSpacesRef.steelNeeded && components >= livingSpacesRef.componentsNeeded && buildingsLimitToday != 0) {
                livingBuildingsOwned++;
                stone -= livingSpacesRef.stoneNeeded;
                steel -= livingSpacesRef.steelNeeded;
                components -= livingSpacesRef.componentsNeeded;
                jobless +=  livingSpacesRef.additionalPpl;
                buildingsLimitToday--;
            } else if (textRequested.equals("make living space")) {
                System.out.println("You lack the needed resources/people or you cant make any more buildings today");
            }

            if(textRequested.equals("make agricultural building") && stone >= agriculturalBuildingsRef.stoneNeeded && steel >= agriculturalBuildingsRef.steelNeeded && components >= agriculturalBuildingsRef.componentsNeeded && jobless >= agriculturalBuildingsRef.pplNeeded && buildingsLimitToday != 0) {
                agriculturalBuildingsOwned++;
                stone -= agriculturalBuildingsRef.stoneNeeded;
                steel -= agriculturalBuildingsRef.steelNeeded;
                components -= agriculturalBuildingsRef.componentsNeeded;
                jobless -= agriculturalBuildingsRef.pplNeeded;
                buildingsLimitToday--;
            } else if (textRequested.equals("make agricultural building")) {
                System.out.println("You lack the needed resources/people or you cant make any more buildings today");
            }

            if(textRequested.equals("make mining plant") && stone >= miningPlantsRef.stoneNeeded && steel >= miningPlantsRef.steelNeeded && components >= miningPlantsRef.componentsNeeded && jobless >= miningPlantsRef.pplNeeded && buildingsLimitToday != 0) {
                miningBuildingsOwned++;
                stone -= miningPlantsRef.stoneNeeded;
                steel -= miningPlantsRef.steelNeeded;
                components -= miningPlantsRef.componentsNeeded;
                jobless -= miningPlantsRef.pplNeeded;
                buildingsLimitToday--;
            } else if (textRequested.equals("make mining plant")) {
                System.out.println("You lack the needed resources/people or you cant make any more buildings today");
            }

            if(textRequested.equals("make manufacturing plant") && stone >= manufacturingPlantsRef.stoneNeeded && steel >= manufacturingPlantsRef.steelNeeded && components >= manufacturingPlantsRef.componentsNeeded && jobless >= manufacturingPlantsRef.pplNeeded && buildingsLimitToday != 0) {
                manufacturingBuildingsOwned++;
                stone -= manufacturingPlantsRef.stoneNeeded;
                steel -= manufacturingPlantsRef.steelNeeded;
                components -= manufacturingPlantsRef.componentsNeeded;
                jobless -= manufacturingPlantsRef.pplNeeded;
                buildingsLimitToday--;
            } else if (textRequested.equals("make manufacturing plant")) {
                System.out.println("You lack the needed resources/people or you cant make any more buildings today");
            }

            if(textRequested.equals("make construction office") && stone >= constructionOfficesRef.stoneNeeded && steel >= constructionOfficesRef.steelNeeded && components >= constructionOfficesRef.componentsNeeded && jobless >= constructionOfficesRef.pplNeeded && buildingsLimitToday != 0) {
                constructionBuildingsOwned++;
                stone -= constructionOfficesRef.stoneNeeded;
                steel -= constructionOfficesRef.steelNeeded;
                components -= constructionOfficesRef.componentsNeeded;
                jobless -= constructionOfficesRef.pplNeeded;
                buildingsLimitToday--;
            } else if (textRequested.equals("make construction plant")) {
                System.out.println("You lack the needed resources/people or you cant make any more buildings today");
            }

            if (textRequested.contains("set daily/plant components")) {
                argumentedCommand = textRequested.split(" ");
                try {
                    componentsPerDay = Integer.parseInt(argumentedCommand[3]);
                }catch (Exception e) {
                    System.out.println("Invalid argument");
                }
            }



            if (textRequested.equals("pass day")) {
                buildingsLimitToday = constructionBuildingsOwned;
                stone += 20 * miningBuildingsOwned;
                steel += 10 * miningBuildingsOwned;
                silicon += 10 * miningBuildingsOwned;
                food -= (livingBuildingsOwned * 50) + jobless;
                if (food <=0) {
                    System.out.println("You have ran out of food, everyone has left");
                    textRequested = "end game";
                }
                food += 100 * agriculturalBuildingsOwned;
                if (componentsPerDay * manufacturingBuildingsOwned <= silicon && componentsPerDay * manufacturingBuildingsOwned <= steel) {
                    silicon -= componentsPerDay * manufacturingBuildingsOwned;
                    steel -= componentsPerDay * manufacturingBuildingsOwned;
                    components += componentsPerDay * manufacturingBuildingsOwned;
                }
            }



            if(textRequested.equals("resources")) {
                System.out.println("Stone: " + stone);
                System.out.println("Steel: " + steel);
                System.out.println("Silicon: " + silicon);
                System.out.println("Components: " + components);
                System.out.println("Food: " + food);
                System.out.println("Jobless: " + jobless);
                System.out.println("Components per day/plant: " + componentsPerDay);
            }

            if(textRequested.equals("buildings")){
                System.out.println("You Have:");
                System.out.println(livingBuildingsOwned + " living spaces");
                System.out.println(miningBuildingsOwned + " mining plants");
                System.out.println(manufacturingBuildingsOwned + " manufacturing plants");
                System.out.println(constructionBuildingsOwned + " construction offices");
                System.out.println(agriculturalBuildingsOwned + " agricultural buildings");
                System.out.println("You can make " + buildingsLimitToday + " more buildings today");
            }

            if (textRequested.equals("help")) {
                System.out.println("make living space: makes a living space that instantly brings in 50 jobless residents");
                System.out.println("make agricultural building: makes an agricultural establishment that generates food every day, note: if you run out of food, the game ends");
                System.out.println("make mining plant: makes a mining plant that gathers stone, steel, and silicon each day");
                System.out.println("make manufacturing plant: makes a factory that creates components based on a setting that can be changed at any time");
                System.out.println("set daily/plant components <number of components created by each plant each day>: depending on the number given, every manufacturing plant will make that many components each day, one component costs 1 silicon and 1 steel");
                System.out.println("make construction office: creates a construction office, the amount of buildings you can make each day is limited by the number of construction offices you have");
                System.out.println();
                System.out.println("pass day: passes a day, all your agricultural, mining, and manufacturing establishments will return resources");
                System.out.println("resources: lists your resources");
                System.out.println("buildings: lists your buildings");
                System.out.println("end game: ends the game");
                System.out.println();
                System.out.println("if you run out of food, the game ends");
                System.out.println();
                System.out.println("type a secret password for developer commands :)");
            }


        }
    }
}