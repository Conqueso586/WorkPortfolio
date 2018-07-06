Connor Nagel
CS453
Hw4



monitor FileAccess{
	
	int sum;
	int max;

FileAccess(int max){

	this.max = max;
	this.sum = 0;

}

void StartAccess(int id){

	while(sum + id > max){
	wait();
}

	sum += id;

}

void EndAccess(int id){

	sum -= id;
	notifyAll();

}

}
