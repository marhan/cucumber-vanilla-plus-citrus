package hellocucumber.cucumber;

class FridayChecker {

    String isItFriday(final String today) {
        if (today.equals("Friday")) {
            return "TGIF";
        }
        return "Nope";
    }
}
