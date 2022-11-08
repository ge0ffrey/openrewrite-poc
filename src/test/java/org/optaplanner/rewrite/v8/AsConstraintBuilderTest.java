package org.optaplanner.rewrite.v8;

import org.junit.jupiter.api.Test;
import org.openrewrite.java.JavaParser;
import org.openrewrite.test.RecipeSpec;
import org.openrewrite.test.RewriteTest;

import static org.openrewrite.java.Assertions.java;

class AsConstraintBuilderTest implements RewriteTest {

    @Override
    public void defaults(RecipeSpec spec) {
        spec.recipe(new AsConstraintBuilder())
            .parser(JavaParser.fromJavaVersion().classpath("optaplanner-core"));
    }

    @Test
    void penalizeStringScore() {
        rewriteRun(
            java("import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;\n" +
                    "import org.optaplanner.core.api.score.stream.bi.BiConstraintStream;\n" +
                    "import org.optaplanner.core.api.score.stream.Constraint;\n" +
                    "\n" +
                    "class Test {\n" +
                    "    Constraint roomConflict(BiConstraintStream<String, String> s) {\n" +
                    "        return s.penalize(\"Room conflict\", HardSoftScore.ONE_HARD);\n" +
                    "    }" +
                    "}\n",
                "import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;\n" +
                    "import org.optaplanner.core.api.score.stream.bi.BiConstraintStream;\n" +
                    "import org.optaplanner.core.api.score.stream.Constraint;\n" +
                    "\n" +
                    "class Test {\n" +
                    "    Constraint roomConflict(BiConstraintStream<String, String> s) {\n" +
                    "        return s.penalize(HardSoftScore.ONE_HARD)\n" +
                    "                .asConstraint(\"Room conflict\");\n" +
                    "    }" +
                    "}\n"));
    }

    @Test
    void penalizeStringScore2() {
        rewriteRun(
            java("import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;\n" +
                    "import org.optaplanner.core.api.score.stream.ConstraintFactory;\n" +
                    "import org.optaplanner.core.api.score.stream.Constraint;\n" +
                    "\n" +
                    "class Test {\n" +
                    "    Constraint roomConflict(ConstraintFactory f) {\n" +
                    "        return f.forEach(String.class).penalize(\"Room conflict\", HardSoftScore.ONE_HARD);\n" +
                    "    }" +
                    "}\n",
                "import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;\n" +
                    "import org.optaplanner.core.api.score.stream.ConstraintFactory;\n" +
                    "import org.optaplanner.core.api.score.stream.Constraint;\n" +
                    "\n" +
                    "class Test {\n" +
                    "    Constraint roomConflict(ConstraintFactory f) {\n" +
                    "        return f.forEach(String.class).penalize(HardSoftScore.ONE_HARD)\n" +
                    "                .asConstraint(\"Room conflict\");\n" +
                    "    }" +
                    "}\n"));
    }

}
