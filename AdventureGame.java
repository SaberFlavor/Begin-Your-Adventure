import java.util.Scanner;

public class AdventureGame {
    private static boolean hasClothes = false;
    private static boolean hasCoffee = false;
    private static boolean hasCleanClothes = false;
    private static String currentLocation = "bedroom"; // Track the player's current location

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String choice;

        System.out.println("Welcome to the Get Ready for Adventure Game!");
        System.out.println("To play the game, type 'left', 'right', 'look', 'pick up', or 'use'. Type 'quit' to end the game.");
        System.out.println("You are woken up by your phone's alarm. It is 7:30 AM and you need to get ready for work.");
        System.out.println("You get out of bed. To your left is your bathroom and the hallway is on your right.");

        while (true) {
            System.out.println("What do you want to do?");

            choice = scanner.nextLine();
            if (choice.equalsIgnoreCase("quit")) {
                System.out.println("Thanks for playing! Goodbye.");
                break;
            }

            if (!processCommand(choice, scanner)) {
                continue; // Skip the prompt if the command was incomplete
            }
        }

        scanner.close();
    }

    public static boolean processCommand(String command, Scanner scanner) {
        String[] parts = command.toLowerCase().split(" ", 2);
        String action = parts[0];
        String target = parts.length > 1 ? parts[1] : "";

        if (action.equals("pick") && target.startsWith("up")) {
            String item = target.length() > 3 ? target.substring(3).trim() : "";
            if (item.isEmpty()) {
                System.out.println("You need to specify what to pick up.");
                return false;
            } else {
                pickUpItem(item);
            }
        } else {
            switch (action) {
                case "left":
                    moveLeft();
                    break;
                case "right":
                    moveRight();
                    break;
                case "look":
                    look();
                    break;
                case "use":
                    useItem(target);
                    break;
                case "bedroom":
                    moveTo("bedroom");
                    break;
                case "kitchen":
                    moveTo("kitchen");
                    break;
                case "living room":
                    moveTo("living room");
                    break;
                default:
                    System.out.println("For some reason, you are confused by what left or right means, so you just go back to bed." +
                                       " You sleep for the whole day, and are only woken up by your phone ringing." +
                                       " You answer the phone. It's your boss. UH OH, sounds like you've been fired!");
            }
        }
        return true;
    }

    public static void moveLeft() {
        switch (currentLocation) {
            case "bedroom":
                System.out.println("You walk into your bathroom.");
                currentLocation = "bathroom";
                break;
            case "hallway":
                System.out.println("You walk into your bedroom.");
                currentLocation = "bedroom";
                break;
            case "kitchen":
                System.out.println("You walk into the living room.");
                currentLocation = "living room";
                break;
            case "living room":
                System.out.println("You walk into the hallway.");
                currentLocation = "hallway";
                break;
            default:
                System.out.println("There's nowhere to go to the left.");
        }
    }

    public static void moveRight() {
        switch (currentLocation) {
            case "bedroom":
                System.out.println("You walk into the hallway.");
                currentLocation = "hallway";
                break;
            case "bathroom":
                System.out.println("You walk into your bedroom.");
                currentLocation = "bedroom";
                break;
            case "hallway":
                System.out.println("You walk into the living room.");
                currentLocation = "living room";
                break;
            case "living room":
                System.out.println("You walk into the kitchen.");
                currentLocation = "kitchen";
                break;
            default:
                System.out.println("There's nowhere to go to the right.");
        }
    }

    public static void moveTo(String location) {
        if (!currentLocation.equals(location)) {
            System.out.println("You walk into your " + location + ".");
            currentLocation = location;
        } else {
            System.out.println("You are already in your " + location + ".");
        }
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
                System.out.println("You take a look around your bathroom. It's small but functional. You see a sink, a mirror, and a shower.");
                System.out.println("To the right is your bedroom.");
                break;
            case "hallway":
                System.out.println("You look around the hallway. It's dimly lit and leads to the rest of the apartment.");
                System.out.println("To the left is your bedroom and to the right is your kitchen.");
                break;
            case "kitchen":
                System.out.println("You look around the kitchen. It's equipped with a stove, refrigerator, and a coffee maker. " +
                                   "There's a bowl of fruit on the counter.");
                System.out.println("To the left is your living room.");
                break;
            case "living room":
                System.out.println("You look around the living room. There's a comfortable couch, a coffee table, and a TV. " +
                                   "The room is well-lit by a large window.");
                                   System.out.println("To the right is your kitchen.");
                break;
            default:
                System.out.println("You look around but don't see anything of interest.");
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
                if (!hasCoffee) {
                    hasCoffee = true;
                    System.out.println("You pick up the cup of coffee. It's lukewarm, but you need the caffeine.");
                } else {
                    System.out.println("You already have the coffee.");
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
                    System.out.println("You drink the coffee and feel more awake.");
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

    public static void useDresser() {
        System.out.println("You open the dresser and see your neatly folded clean clothes. They look fresh and ready to wear.");
    }

    public static void useNightstand() {
        System.out.println("You open the drawer on your nightstand. There's nothing useful.");
    }
}
