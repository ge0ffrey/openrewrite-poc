package com.yourorg;

import org.junit.jupiter.api.Test;
import org.openrewrite.java.JavaParser;
import org.openrewrite.test.RecipeSpec;
import org.openrewrite.test.RewriteTest;

import static org.openrewrite.java.Assertions.java;

class NoGuavaListsNewArrayListTest implements RewriteTest {

    //Note, you can define defaults for the RecipeSpec and these defaults will be used for all tests.
    //In this case, the recipe and the parser are common. See below, on how the defaults can be overridden
    //per test.
    @Override
    public void defaults(RecipeSpec spec) {
        spec.recipe(new NoGuavaListsNewArrayList())
            .parser(JavaParser.fromJavaVersion()
                .logCompilationWarningsAndErrors(true)
                .classpath("guava"));
    }

    @Test
    void replaceWithNewArrayList() {
        rewriteRun(
            //There is an overloaded version or rewriteRun that allows the RecipeSpec to be customized specifically
            //for a given test. In this case, the parser for this test is configured to not log compilation warnings.
            spec -> spec
                .parser(JavaParser.fromJavaVersion()
                    .logCompilationWarningsAndErrors(false)
                    .classpath("guava")),
            java("    import com.google.common.collect.*;\n" +
                    "\n" +
                    "    import java.util.List;\n" +
                    "\n" +
                    "    class Test {\n" +
                    "        List<Integer> cardinalsWorldSeries = Lists.newArrayList();\n" +
                    "    }\n",
                "    import java.util.ArrayList;\n" +
                    "    import java.util.List;\n" +
                    "\n" +
                    "    class Test {\n" +
                    "        List<Integer> cardinalsWorldSeries = new ArrayList<>();\n" +
                    "    }\n"));
    }

    @Test
    void replaceWithNewArrayListIterable() {
        rewriteRun(
            java("    import com.google.common.collect.*;\n" +
                    "\n" +
                    "    import java.util.Collections;\n" +
                    "    import java.util.List;\n" +
                    "\n" +
                    "    class Test {\n" +
                    "        List<Integer> l = Collections.emptyList();\n" +
                    "        List<Integer> cardinalsWorldSeries = Lists.newArrayList(l);\n" +
                    "    }\n",
                "    import java.util.ArrayList;\n" +
                    "    import java.util.Collections;\n" +
                    "    import java.util.List;\n" +
                    "\n" +
                    "    class Test {\n" +
                    "        List<Integer> l = Collections.emptyList();\n" +
                    "        List<Integer> cardinalsWorldSeries = new ArrayList<>(l);\n" +
                    "    }\n"));
    }

    @Test
    void replaceWithNewArrayListWithCapacity() {
        rewriteRun(
            java("    import com.google.common.collect.*;\n" +
                    "\n" +
                    "    import java.util.ArrayList;\n" +
                    "    import java.util.List;\n" +
                    "\n" +
                    "    class Test {\n" +
                    "        List<Integer> cardinalsWorldSeries = Lists.newArrayListWithCapacity(2);\n" +
                    "    }\n",
                "    import java.util.ArrayList;\n" +
                    "    import java.util.List;\n" +
                    "\n" +
                    "    class Test {\n" +
                    "        List<Integer> cardinalsWorldSeries = new ArrayList<>(2);\n" +
                    "    }\n"));
    }
}
