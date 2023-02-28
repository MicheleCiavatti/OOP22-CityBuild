package it.unibo.view;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import it.unibo.controller.api.Controller;
import it.unibo.model.api.Resource;

/**An utility class for the text of labels in {@link it.unibo.view.GameScreen}. */
public class Description {

    /*The keys are the name of the building, the values are the descriptions 
    in order (revenue, construction, upgrade), with also the name of the building as first line */
    private final Map<String, List<String>> descriptions;
    
    public Description(final Controller controller) {
        this.descriptions = new HashMap<>();
        Arrays.stream(Resource.values())
            .flatMap(r -> Stream.of(r.getSimpleBuilding(), r.getAdvancedBuilding()))
            .map(name -> name.replace("_", " "))
            .forEach(name -> descriptions.put(name, List.of(
                tableToDescription(controller.getRevenue(name), name),
                tableToDescription(controller.getCost(name), name),
                tableToDescription(controller.getUpgrade(name), name)
            )));
    }

    public String revenueText(final String name) {
        return this.descriptions.get(name.replace("_", " ")).get(0);
    }

    public String constructiontext(final String name) {
        return this.descriptions.get(name.replace("_", " ")).get(1);
    }

    public String upgradeText(final String name) {
        return this.descriptions.get(name.replace("_", " ")).get(2);
    }

    private String tableToDescription(final Map<Resource, Integer> table, final String name) {
        final StringBuilder str = new StringBuilder(name);
        table.entrySet().stream()
            .map(entry -> "\n" + entry.getKey() + ": " + entry.getValue())
            .forEach(str::append);
        return str.toString();
    }
}
