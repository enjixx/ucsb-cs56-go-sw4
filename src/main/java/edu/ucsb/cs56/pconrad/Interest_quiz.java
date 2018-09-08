package edu.ucsb.cs56.pconrad;

import static spark.Spark.port;

import org.apache.log4j.Logger;


import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import spark.template.mustache.MustacheTemplateEngine;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFiles;


/**
 * Simple example of using Mustache Templates
 *
 */

public class Interest_quiz {

	public static final String CLASSNAME="SparkMustacheDemo02";

	public static final Logger log = Logger.getLogger(CLASSNAME);

	public static void main(String[] args) {

        port(getHerokuAssignedPort());
    staticFiles.location("templates/public");
		Map map = new HashMap();
        map.put("name", "User");
    // Map map2 = new HashMap();
		//     map2.put("feedback","Hollywood");
        // hello.mustache file is in resources/templates directory
        get("/", (rq, rs) -> new ModelAndView(map, "hello.mustache"), new MustacheTemplateEngine());

		get("/fill_quiz", (rq, rs) -> new ModelAndView(map, "fill_quiz.mustache"), new MustacheTemplateEngine());

		post("/feedback", (rq, rs) ->
			{
				Map quiz_map = new HashMap();
				if((rq.queryParams("q1")).equals("NC")){
					if((rq.queryParams("q2")).equals("close")){
						if((rq.queryParams("q3")).equals("cheap")){
							if((rq.queryParams("q4")).equals("urban")){
								quiz_map.put("feedback", "Santa Cruz Boardwalk");
							}
							else{
								quiz_map.put("feedback", "Mission Peak Hiking Trail");
							}
						}
						else{
							if((rq.queryParams("q4")).equals("urban")){
								quiz_map.put("feedback", "Monterey Bay Aquarium");
							}
							else{
								quiz_map.put("feedback", "Yosemite Valley Camping");
							}
						}
					}
						else{
							if((rq.queryParams("q3")).equals("cheap")){
								if((rq.queryParams("q4")).equals("urban")){
									quiz_map.put("feedback", "Golden Gate Bridge");
								}
								else{
									quiz_map.put("feedback", "Japanese Tea Garden");
								}
							}
							else{
								if((rq.queryParams("q4")).equals("urban")){
									quiz_map.put("feedback", "Ice Cream Museum");
								}
								else{
									quiz_map.put("feedback", "Lake Tahoe Camping");
								}
							}
					}
				}
					else{
						if((rq.queryParams("q2")).equals("close")){
							if((rq.queryParams("q3")).equals("cheap")){
								if((rq.queryParams("q4")).equals("urban")){
									quiz_map.put("feedback", "Hollywood Sign");
									quiz_map.put("imgPath", "Hollywood.jpg");
								}
								else{
									quiz_map.put("feedback", "Malibu Beach");
								}
							}
							else{
								if((rq.queryParams("q4")).equals("urban")){
									quiz_map.put("feedback", "DisneyLand");
								}
								else{
									quiz_map.put("feedback", "Santa Catalina Island");
								}
							}
						}
						else{
							if((rq.queryParams("q3")).equals("cheap")){
								if((rq.queryParams("q4")).equals("urban")){
									quiz_map.put("feedback", "Palm Springs Air Museum");
								}
								else{
									quiz_map.put("feedback", "Joshua Tree National Park");
								}
							}
							else{
								if((rq.queryParams("q4")).equals("urban")){
									quiz_map.put("feedback", "San Diego SeaWorld");
								}
								else{
									quiz_map.put("feedback", "Palm Springs Air Trampway");
								}
							}
							quiz_map.put("feedback", "Please follow our instruction for input");
						}
					}
				return new ModelAndView(quiz_map, "feedback.mustache");
			}, new MustacheTemplateEngine());
}

			// new ModelAndView(map, "addedStudent.mustache"), new MustacheTemplateEngine());



    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }


}
