all: mydash-project 
#dox

mydash-project: 
	cd mydash-src; make

dox:
	echo "Generating documentation using doxygen..."
	doxygen doxygen-config > doxygen.log 
	echo "Use konqueror docs/html/index.html to see docs (or any other browser)"

clean:
	/bin/rm -rf mydash docs doxygen.log;
	cd mydash-src; make clean

	


