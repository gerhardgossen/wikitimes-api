# WikiTimes Java API #

This library provides access to the [WikiTimes](http://wikitimes.l3s.de/) REST API. It requires Java 8.

## Usage ##

Include through Maven by adding the following snippets to your `pom.xml`:

	<repositories>
		<repository>
			<id>icrawl-snapshots</id>
			<url>http://maven.l3s.uni-hannover.de:9088/nexus/content/repositories/icrawl_snapshots/</url>
		</repository>
	</repositories>

and

	<dependency>
		 <groupId>de.l3s</groupId>
		 <artifactId>wikitimes-api</artifactId>
		 <version>0.0.1-SNAPSHOT</version>
	</dependency>

Use it in your code like this:

	WikiTimes wikiTimes = new WikiTimes();
	System.out.printf("%5s: %-50s %s%n", "ID", "Name", "URL");
	Collection<Story> stories = wikiTimes.getStoriesWithoutTimeline();
	for (Story story : stories) {
        System.out.printf("%5d: %-50s %s%n", story.getId(), story.getName(),
            story.getWikipediaUrl());
	}

## License ##

Copyright 2014 Gerhard Gossen

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
