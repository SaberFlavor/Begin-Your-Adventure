import java.util.Scanner;

public class AdventureGame {
    private static boolean hasClothes = false;
    private static boolean hasCoffee = false;
    private static boolean hasCleanClothes = false;
    private static boolean coffeeInProgress = false;
    private static boolean coffeeReady = false;
    private static boolean hasEggs = false;
    private static String currentLocation = "bedroom"; // Track the player's current location

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String choice;

        System.out.println("Welcome to the Get Ready for Adventure Game!");
        System.out.println("To play the game, type 'go to [location]', 'look', 'pick up', or 'use'. Type 'quit' to end the game.");
        System.out.println("You are woken up by your phone's alarm. It is 7:30 AM and you need to get ready for work.");
        System.out.println("You get out of bed. You can see your ensuite bathroom and the hallway leading to your living room and kitchen.");

        while (true) {
            System.out.println("What do you want to do?");

            choice = scanner.nextLine();
            if (choice.equalsIgnoreCase("quit")) {
                System.out.println("Thanks for playing! Goodbye.");
                break;
            }

            if (!processCommand(choice, scanner)) {
                continue;
            }
        }

        scanner.close();
    }

    public static boolean processCommand(String command, Scanner scanner) {
        String[] parts = command.toLowerCase().split(" ", 3);
        String action = parts[0];
        String preposition = parts.length > 1 ? parts[1] : "";
        String location = parts.length > 2 ? parts[2] : "";

        if (action.equals("pick") && preposition.equals("up")) {
            if (location.isEmpty()) {
                System.out.println("You need to specify what to pick up.");
                return false;
            } else {
                pickUpItem(location);
                return true; 
            }
        } else if (action.equals("go") && preposition.equals("to")) {
            moveTo(location);
        } else {
            switch (action) {
                case "look":
                    look();
                    break;
                case "use":
                    useItem(location);
                    break;
                default:
                    System.out.println("For some reason, you feel lost in your own home. You go back to bed to sleep off the confusion. You wake up to your phone ringing. "+
                    "You answer the phone. UH OH! It's your boss and they don't sound too happy. You should get ready for work!");
            }
        }
        return true;
    }

    public static void moveTo(String location) {
        switch (location.toLowerCase()) {
            case "bedroom":
                System.out.println("You walk into your bedroom.");
                currentLocation = "bedroom";
                break;
            case "bathroom":
                System.out.println("You walk into the bathroom.");
                currentLocation = "bathroom";
                break;
            case "hallway":
                System.out.println("You walk into the hallway.");
                currentLocation = "hallway";
                break;
            case "kitchen":
                System.out.println("You walk into the kitchen.");
                currentLocation = "kitchen";
                break;
            case "living room":
                System.out.println("You walk into the living room.");
                currentLocation = "living room";
                break;
            default:
                System.out.println("You can't go to " + location + ".");
        }
    }

    public static void pickUpItem(String item) {
        switch (item.toLowerCase()) {
            case "clothes":
                if (!hasClothes) {
                    hasClothes = true;
                    System.out.println("You pick up your old dirty clothes. They smell a bit.");
                } else {
                    System.out.println("You are already holding your old dirty clothes.");
                }
                break;
            case "coffee":
                if (coffeeReady) {
                    hasCoffee = true;
                    coffeeReady = false; // Coffee is now picked up
                    System.out.println("You pick up the cup of coffee. It's hot and fresh.");
                } else {
                    System.out.println("The coffee is not ready yet.");
                }
                break;
            case "clean clothes":
                if (!hasCleanClothes) {
                    hasCleanClothes = true;
                    System.out.println("You pick up your clean clothes from the dresser. They are fresh and ready to wear.");
                } else {
                    System.out.println("You already have the clean clothes.");
                }
                break;
            case "eggs":
                if (currentLocation.equals("kitchen")) {
                    hasEggs = true;
                    System.out.println("You pick up some eggs from the fridge.");
                } else {
                    System.out.println("You already have eggs in your hands.");
                }
                break;
            default:
                System.out.println("There is no " + item + " to pick up.");
        }
    }

    public static void useItem(String target) {
        switch (target.toLowerCase()) {
            case "clothes":
                if (hasClothes) {
                    System.out.println("You put on your old dirty clothes. Surely no one will notice.");
                } else {
                    System.out.println("You don't have any clothes to use.");
                }
                break;
            case "coffee":
                if (hasCoffee) {
                    System.out.println("You drink the coffee and feel more awake."); //adding fuction to code from previous version
                } else {
                    System.out.println("You don't have any coffee to use.");
                }
                break;
            case "clean clothes":
                if (hasCleanClothes) {
                    System.out.println("You put on your clean clothes. You feel fresh and ready for the day.");
                } else {
                    System.out.println("You don't have any clean clothes to use.");
                }
                break;
            case "coffee maker":
                useCoffeeMaker();
                break;
            case "stove":
                if (hasEggs) {
                    System.out.println("You cook the eggs on the stove. They are perfectly cooked and ready to eat.");
                    hasEggs = false; // Eggs are used up
                } else {
                    System.out.println("You don't have anything to cook.");
                }
                break;
            case "sink":
                System.out.println("You wash your hands in the sink. You really need to buy soap someday.");
                break;
            case "fridge": // kitchen uses are broken for some reason
                useFridge();
                break;
            case "dresser":
                useDresser();
                break;
            case "nightstand":
                useNightstand();
                break;
            default:
                System.out.println("You can't use the " + target + ".");
        }
    }

    public static void useCoffeeMaker() {
        if (currentLocation.equals("kitchen")) {
            if (!coffeeInProgress && !coffeeReady) {
                coffeeInProgress = true;
                System.out.println("You start the coffee maker. It will take a few minutes to brew.");
                // Not actually timed slash kind of broken
                coffeeInProgress = false;
                coffeeReady = true;
                System.out.println("The coffee is ready. You can pick it up now.");
            } else if (coffeeInProgress) {
                System.out.println("The coffee is still brewing. Please wait.");
            } else {
                System.out.println("The coffee is already ready.");
            }
        } else {
            System.out.println("There is no coffee maker here.");
        }
    }

    public static void useFridge() {
        if (currentLocation.equals("kitchen")) {
            System.out.println("You look inside the fridge and see some eggs. You're feeling a little hungry.");
        } else {
            System.out.println("There is no fridge here.");
        }
    }

    public static void useDresser() {
        System.out.println("You open the dresser and see your neatly folded clean clothes. They look fresh and ready to wear.");
    }

    public static void useNightstand() {
        System.out.println("You open the drawer on your nightstand. There's nothing useful.");
    }

    public static void look() {
        switch (currentLocation) {
            case "bedroom":
                System.out.println("You take a moment to look around. Your room is rather spartan. " +
                                   "You don't really care about decorating.");
                System.out.println("The sunlight peeks through the blinds, letting you know it's morning. You see your nightstand beside your bed,");
                System.out.println("your dresser across the room, " +
                                   "and the clothes you wore yesterday scattered across the floor. There is nothing out of the ordinary. " +
                                   "You need to get ready.");
                break;
            case "bathroom":
                System.out.println("You take a look around your bathroom. It's small but functional. You see a sink, a mirror, and a shower."); // add more to look at
                break;
            case "hallway":
                System.out.println("You look around the hallway. It's dimly lit and leads to the rest of the apartment."); // add more to the hallway. maybe pictures to look at?
                break;
            case "kitchen":
                System.out.println("You look around the kitchen. It's equipped with a sink, stove, fridge, and coffee maker. " +
                                   "There's a bowl of fruit on the counter.");
                break;
            case "living room":
                System.out.println("You look around the living room. There's a comfortable couch, a coffee table, and a TV. " +
                                   "The room is well-li by the morning sun through a large window.");
                break;
            default:
                System.out.println("You look around but don't see anything of interest.");
        }
    }
}
