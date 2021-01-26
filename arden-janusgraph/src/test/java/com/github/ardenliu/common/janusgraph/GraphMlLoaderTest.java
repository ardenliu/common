package com.github.ardenliu.common.janusgraph;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.xmlunit.assertj.XmlAssert.assertThat;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.structure.Direction;
import org.apache.tinkerpop.gremlin.structure.Edge;
import org.apache.tinkerpop.gremlin.structure.Graph;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.apache.tinkerpop.gremlin.tinkergraph.structure.TinkerGraph;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.xmlunit.diff.DefaultNodeMatcher;
import org.xmlunit.diff.ElementSelectors;

import com.github.ardenliu.common.file.ResourcesUtils;

class GraphMlLoaderTest {
    
    static Graph newGraph;

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        newGraph = TinkerGraph.open();
        GraphTraversalSource graphTraversalSource = newGraph.traversal();
        
        Vertex marko = graphTraversalSource.addV("person").property("name", "marko").next();
        System.out.println(marko);
               
        Vertex stephen = graphTraversalSource.addV("person").property("name", "stephen").next();
        System.out.println(stephen);
        
        Edge knows = graphTraversalSource.V(marko).addE("knows").to(stephen).property("weight", 0.75).next();
        System.out.println(knows);
        
        System.out.println(newGraph);
    }
    
    @Test
    void loadGraphFromFile(@TempDir Path tempDir) {
        String resourcePath = "com/github/ardenliu/common/janusgraph/graphml";
        ResourcesUtils.copyFromClassPath(resourcePath, tempDir);
    
        Graph graph = GraphMlLoader.loadGraphFromFile(Paths.get(tempDir.toString(), resourcePath, "GraphMlLoaderTest_NewGraph.xml").toString());
        System.out.println(graph);

        verify(graph);
    }

    @Test
    void loadGraphFromClassPath() {
        String classpath = "/com/github/ardenliu/common/janusgraph/graphml/GraphMlLoaderTest_NewGraph.xml";
        Graph graph = GraphMlLoader.loadGraphFromClassPath(classpath);
        System.out.println(graph);
        
        verify(graph);
    }
    
    @Test
    void getGraphMlString() {
        String newGraphString = GraphMlLoader.getGraphMlString(newGraph);
        System.out.println(newGraphString);
        
        String expected = ResourcesUtils.resourceToString("com/github/ardenliu/common/janusgraph/graphml/GraphMlLoaderTest_NewGraph.xml");
        System.out.println(expected);
        
        assertThat(newGraphString).and(expected)
        .ignoreComments()
        .ignoreChildNodesOrder()
        .ignoreWhitespace()
        .withNodeMatcher(new DefaultNodeMatcher(ElementSelectors.byNameAndAttributes("id", "key")))
        .areIdentical();
    }
    
    private void verify(Graph graph) {
        GraphTraversalSource graphTraversalSource = graph.traversal();
        
        Vertex marko = graphTraversalSource.V().has("person", "name", "marko").next();
        System.out.println(marko);
        assertEquals("person", marko.label());
        assertEquals("marko", marko.value("name"));
        
        Vertex stephen = graphTraversalSource.V().has("person", "name", "stephen").next();
        System.out.println(marko);
        assertEquals("person", stephen.label());
        assertEquals("stephen", stephen.value("name"));
        
        Edge knows = marko.edges(Direction.OUT, "knows").next();
        assertEquals(knows.property("weight").value(),  0.75);
    }
}