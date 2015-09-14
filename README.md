# SoftwareStore
This project was uploaded for testing purposes only... I used as an example project to push from a development environment to GITHUB..
and to be picked up by a BuildServer using GIT / Jenkins..

The project itself is an attempt to prove that component based development differs from object oriented development.
I chose the MVC pattern implementation because I think that in OO this still binds the objects more than necessary. Therefore I built all the components needed to create a desktop clock separate
let them communicate via a (certain) context. Each component can send/publish events to the context or register to be notified about events occurring in the context.

This way I reuse each component to the max..... For instance to run multiple clocks I need only 1 timer in the context to produce the necessary timer events.
I just need to put another clock in the same context and have it registered to listen to the same timer-events to get it running.
