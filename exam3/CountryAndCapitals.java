import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Scanner;

class CountryAndCapitals{
    public static void main(String[] args) {
        HashMap<String, HashMap<String, String>> countries = new LinkedHashMap<>();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of contries");
        int n = sc.nextInt();
        sc.nextLine();
        final String seasons[] = {"winter", "summer", "rainy"};
        for (int i = 0; i < n; i++) {
            System.out.println("Enter "+(i+1)+" country name");
            String country = sc.nextLine();
            HashMap<String, String> capitals = new LinkedHashMap<>();
            System.out.println("Enter number of capitals");
            int m = sc.nextInt();
            sc.nextLine();
            if(m == 1){
                System.out.println("Enter capital name");
                String capital = sc.nextLine();
                capitals.put("capital", capital);
            }
            else if(m == 2){
                for (int j = 0; j < m; j++) {
                    System.out.println("Enter "+seasons[j]+" season capital name");
                    String capital = sc.nextLine();
                    capitals.put(seasons[j], capital);
                }
            }
            else{
                for (int j = 0; j < m; j++) {
                    System.out.println("Enter "+seasons[j]+" season capital name");
                    String capital = sc.nextLine();
                    capitals.put(seasons[j], capital);
                }
            }
            countries.put(country, capitals);
        }
        choice(countries, sc);
        sc.close();
    }

    public static void choice(HashMap<String, HashMap<String, String>> countries, Scanner sc){
        CountryAndCapitals obj = new CountryAndCapitals();
        while(true){
            System.out.println("1. Display all countries and their capitals");
            System.out.println("2. Display  countries having three capitals");
            System.out.println("3. Display  countries having two capitals");
            System.out.println("4. Display  capitals based on country");
            System.out.println("5. Display  summer capitals starting with vowels");
            System.out.println("6. Display  capitals starting with vowels");
            System.out.println("7. Display  countries with no summer capitals");
            System.out.println("8. Exit");
            System.out.println("Enter your choice");
            int choice = sc.nextInt();  
            switch (choice) {
                case 1: obj.countryAndCapitals(countries);break;
                case 2: obj.countriesHavingThreeCapitals(countries);break;
                case 3: obj.countriesHavingTwoCapitals(countries);break;
                case 4: {
                    System.out.println("Enter country name");
                    String country = sc.next();
                    obj.capitalsBasedOnCountry(countries, country);};break;
                case 5: obj.summerCapitalsStartsWithVowels(countries);break;
                case 6: obj.capitalStartsWithVowels(countries);break;
                case 7: obj.countriesWithNoSummerCapitals(countries);break;
                case 8: System.exit(0);break;
                default:System.out.println("Invalid choice");break;
            }
        }
    }

    public void summerCapitalsStartsWithVowels(HashMap<String, HashMap<String, String>> countries) {
        System.out.println("----------Summer capitals starting with vowels----------");
        String vowels = "aeiouAEIOU";
        for (String country : countries.keySet()) {
            HashMap<String, String> capitals = countries.get(country);
            if (capitals.containsKey("summer")) {
                String summerCapital = capitals.get("summer");
                if (summerCapital != null && !summerCapital.trim().isEmpty() && vowels.contains(summerCapital.substring(0, 1))) {
                    System.out.println(summerCapital);
                }
            }
        }
    }

     public void capitalStartsWithVowels(HashMap<String, HashMap<String, String>> countries) {
        System.out.println("----------Capitals starting with vowels----------");
        String vowels = "aeiouAEIOU";
        for (String country : countries.keySet()) {
            HashMap<String, String> capitals = countries.get(country);
            for (String capital : capitals.values()) {
                if (capital != null && !capital.trim().isEmpty() && vowels.contains(capital.substring(0, 1))) {
                    System.out.println(capital);
                }
            }
        }
    }

    public void countriesWithNoSummerCapitals(HashMap<String, HashMap<String, String>> countries) {
        System.out.println("----------Countries with no summer capitals----------");
        for (String country : countries.keySet()) {
            HashMap<String, String> capitals = countries.get(country);
            if (!capitals.containsKey("summer")) {
                System.out.println(country);
            }
        }
    }

    public void countriesHavingThreeCapitals(HashMap<String, HashMap<String, String>> countries){
        System.out.println("----------Countries having three capitals----------");
        for(String country : countries.keySet()){
            HashMap<String, String> capitals = countries.get(country);
            if(capitals.size() == 3){
                System.out.println(country);
            }
        }
    }

    public void countriesHavingTwoCapitals(HashMap<String, HashMap<String, String>> countries){
        System.out.println("----------Countries having two capitals----------");
        for(String country : countries.keySet()){
            HashMap<String, String> capitals = countries.get(country);
            if(capitals.size() == 2){
                System.out.println(country);
            }
        }
    }

    public void capitalsBasedOnCountry(HashMap<String, HashMap<String, String>> countries, String country){
        System.out.println("----------Capitals based on Country----------");
        if(countries.containsKey(country)){
            HashMap<String, String> capitals = countries.get(country);
            System.out.println("Number of capitals: "+capitals.size());
            for (String season : capitals.keySet()) {   
                if(season.equals("capital")){
                    System.out.println("One capital: "+capitals.get(season));
                }
                else{
                     System.out.println(season+" capital: "+capitals.get(season));
                }
            }
        }
        else{
            System.out.println("No such country");
        }
    }

    public void countryAndCapitals(HashMap<String, HashMap<String, String>> countries){
        System.out.println("----------Countries and Capitals----------");
        for (String country : countries.keySet()) {
            System.out.println(country);
            HashMap<String, String> capitals = countries.get(country);
            System.out.println("Number of capitals: "+capitals.size());
            for (String season : capitals.keySet()) {   
                if(season.equals("capital")){
                    System.out.println("Common Capital: "+capitals.get(season));
                }
                else{
                    System.out.println(season+" capital: "+capitals.get(season));
                }
            }
        }
    }
}