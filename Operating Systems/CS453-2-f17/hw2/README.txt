Name: Connor Nagel
Date: 10/17/2017
Class: CS453

Initially I had difficulties getting the kernel to build and install correctly. 
I kept receiving an error saying that there was an issue with vbox.add. It took
me a while to notice that the kernel was still properly building regardless of this error.
Once I could build and install the kernel I had to find the code that needed to be changed.
To find this code I ran a search on all the occurances of MAX_THREADS in fork.c.
I found the __init initfork function that called the setMaxThreads function. Below that call 
multiple variables were initialized using maxthreads / 2. I assumed that this was where ulimit got
its value so I changed maxthreads / 2 to (maxthreads / 20) * 19 to set it to 95%. Then I rebuilt
and installed the new kernel. Once I rebooted I found that the value in ulimit had changed to 28671.
