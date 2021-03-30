package seedu.address.logic.filters.combinator;

import seedu.address.logic.filters.AbstractFilter;
import seedu.address.logic.filters.Filters;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Prefix;
import seedu.address.model.customer.Customer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.function.Predicate;

import static seedu.address.logic.parser.CliSyntax.PREFIX_OR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOT;


public class FilterCombinator implements Predicate<Customer> {
    private final Node rootNode;

    public FilterCombinator(String argument) {
        rootNode = createTree(argument);
    }

    private LogicalOperator getCorrespondingLogicalOperator(Prefix prefix) {
        if (prefix.equals(PREFIX_AND)) {
            return LogicalOperator.AND;
        } else if (prefix.equals(PREFIX_OR)) {
            return LogicalOperator.OR;
        } else {
            return LogicalOperator.NOT;
        }
    }


    private Node createTree(String description) {
        description = description.trim();
        if (description.replace('[', ' ').replace(']', ' ').trim().isEmpty()) {
            throw new IllegalArgumentException("Incorrect formatting 1");
        }

        StringBuilder inPresentScope = new StringBuilder("");
        StringBuilder inSubtreeScope = new StringBuilder("");

        Stack<Node> nodeStack = new Stack<>();
        LinkedList<ArgumentTokenizer.PrefixPosition> allPositions =
                new LinkedList<>(ArgumentTokenizer.findAllPrefixPositions(description, PREFIX_AND,
                        PREFIX_NOT, PREFIX_OR));

        allPositions.sort(Comparator.comparingInt(ArgumentTokenizer.PrefixPosition::getStartPosition));

        System.out.println(allPositions);

        int nestingLevel = 0;

        for (int i = 0; i < description.length(); i++) {
            char c = description.charAt(i);
            // first check if the character is part of a logical operator prefix
            if (!allPositions.isEmpty() && i == allPositions.getFirst().getStartPosition()) {
                // now we try to form a Node from the previously given filter at this level.
                // only try to form a node if we have actually got some information
                if (inPresentScope.toString().trim().length() > 0) {
                    AbstractFilter filter = Filters.getCorrespondingFilter(inPresentScope.toString().trim());
                    nodeStack.push(new Node(filter));
                }

                Prefix prefix = allPositions.getFirst().getPrefix();
                nodeStack.push(new Node(getCorrespondingLogicalOperator(prefix)));
                i += prefix.getPrefix().trim().length() - 1; // since we will anyway do a +1 when the for loop updates.

                allPositions.removeFirst();
                inPresentScope = new StringBuilder("");

                continue;
            }

            // all nesting is handled here.
            if (c == '[') {
                // starting a new nesting depth
                if (nestingLevel != 0) {
                    inSubtreeScope.append(c);
                }
                nestingLevel++;

            } else if (c == ']') {
                nestingLevel--;
                if (nestingLevel > 0) {
                    inSubtreeScope.append(c);
                } else {
                    Node nextNode = createTree(inSubtreeScope.toString());
                    nodeStack.push(nextNode);
                }
            } else {
                inPresentScope.append(c);
            }
        }

        if (inPresentScope.toString().trim().length() > 0) {
            AbstractFilter filter = Filters.getCorrespondingFilter(inPresentScope.toString().trim());
            nodeStack.push(new Node(filter));
        }

        System.out.println(nodeStack);

        return formTreeFromNodeStack(nodeStack);
    }

    private Node formTreeFromNodeStack(Stack<Node> nodeStack) {
        while (!nodeStack.isEmpty()) {
            if (nodeStack.size() == 1) {
                return nodeStack.pop();
            }

            if (nodeStack.size() == 2) {
                Node upper = nodeStack.pop();
                Node operator = nodeStack.pop();
                if (!operator.hasUnaryOperator()) {
                    throw new IllegalArgumentException("Incorrect formatting 3");
                }
                operator.setChild(upper);
                nodeStack.push(operator);
            }

            Node first = nodeStack.pop();
            Node second = nodeStack.pop();
            Node third = nodeStack.pop();

            if (second.hasChildren() || second.getNodeType() == NodeType.EVALUATOR) {
                throw new IllegalArgumentException("Incorrect formatting 4 " + second.getNodeType());
            }

            if (second.hasUnaryOperator()) {
                second.setChild(first);
                nodeStack.push(third);
                nodeStack.push(second);
            } else {
                second.setLeftNode(third);
                second.setRightNode(first);
                nodeStack.push(second);
            }


        }

        throw new IllegalArgumentException("Incorrect formatting 5");
    }

    @Override
    public boolean test(Customer customer) {
        return rootNode.evaluate(customer);
    }

    @Override
    public String toString() {
        return "FilterCombinator{" +
                "rootNode=" + rootNode +
                '}';
    }
}