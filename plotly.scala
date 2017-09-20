class Plotly() {
    
    val id = hashCode
    
    drawCanvas
    
    def renderPlotlyChart(data:AnyRef) {
        import org.json4s._
        import org.json4s._
        import org.json4s.native.Serialization
        import org.json4s.native.Serialization.{read, write}
        implicit val formats = Serialization.formats(NoTypeHints)
        
        val sdata = write(data)

        val html = s"""%html
            <script>
                var data = $sdata;
                var layout = {
                    margin: {
                        l: 0,
                        r: 0,
                        b: 0,
                        t: 0
                    }
                };
                var options = {displayModeBar: false}
                plotyDiv = document.getElementById('plotly_$id')
                Plotly.newPlot(plotyDiv, data, layout, options);
            </script>
        """
        println(html)
    }
    
    def drawCanvas() {
        val canvas = s"""%html
          <script
          src="https://code.jquery.com/jquery-2.2.4.min.js"
          integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44="
          crossorigin="anonymous"></script>
          <script src="https://cdn.plot.ly/plotly-latest.js"></script>
          <div id="plotly_$id" style="min-width: 310px; min-height: 300px; margin: 0 auto"></div>
          """
        println(canvas)
    }
    
    def example() {
        
        val data = Array(
                    Map(
                        "name" -> "abc",
                        "x" -> Seq(1,2,3),
                        "y" -> Seq(1,2,3),
                        "z" -> Seq(1,2,3),
                        "type" -> "scatter3d",    
                        "mode" -> "markers",
                        "marker" -> Map(
                            "size" -> 3, 
                            "color" -> "red",
                            "line" -> Map("width" -> 0),
                            "opacity" -> 0.9)
                    )
                )
        
        renderPlotlyChart(data)
    }
}
