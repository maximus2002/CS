# Project 3 for CS 6250: Computer Networks
#
# This defines a DistanceVector (specialization of the Node class)
# that can run the Bellman-Ford algorithm. The TODOs are all related 
# to implementing BF. Students should modify this file as necessary,
# guided by the TODO comments and the assignment instructions. This 
# is the only file that needs to be modified to complete the project.
#
# Student code should NOT access the following members, otherwise they may violate
# the spirit of the project:
#
# topolink (parameter passed to initialization function)
# self.topology (link to the greater topology structure used for message passing)
#
# Copyright 2017 Michael D. Brown
# Based on prior work by Dave Lillethun, Sean Donovan, and Jeffrey Randow.
        											
from Node import *
from helpers import *

class DistanceVector(Node):
    
    def __init__(self, name, topolink, outgoing_links, incoming_links):
        ''' Constructor. This is run once when the DistanceVector object is
        created at the beginning of the simulation. Initializing data structure(s)
        specific to a DV node is done here.'''

        super(DistanceVector, self).__init__(name, topolink, outgoing_links, incoming_links)
        
        #TODO: Create any necessary data structure(s) to contain the Node's internal state / distance vector data    
        self.vector = {name:0}

    def send_to_incoming_links(self):
        for link in self.incoming_links:
            message = { "source": self.name, "vector": self.vector, "dest": link }
            self.send_msg(message, link.name)

    def is_outgoing_neighbor(self, nodeName):
        for link in self.outgoing_links:
            if nodeName == link.name:
                return True

    def send_initial_messages(self):
        ''' This is run once at the beginning of the simulation, after all
        DistanceVector objects are created and their links to each other are
        established, but before any of the rest of the simulation begins. You
        can have nodes send out their initial DV advertisements here. 

        Remember that links points to a list of Neighbor data structure.  Access
        the elements with .name or .weight '''

        # TODO - Each node needs to build a message and send it to each of its neighbors
        # HINT: Take a look at the skeleton methods provided for you in Node.py
        self.send_to_incoming_links()

    def process_BF(self):
        ''' This is run continuously (repeatedly) during the simulation. DV
        messages from other nodes are received here, processed, and any new DV
        messages that need to be sent to other nodes as a result are sent. '''

        # Implement the Bellman-Ford algorithm here.  It must accomplish two tasks below:
        # TODO 1. Process queued messages       
        update_distance = False
        for msg in self.messages:         
            for node in msg["vector"].keys():
                # print(msg["vector"].keys())
                # print(self.name)
                # print(msg["vector"][node])
        #if node is not in DV and is not local node, then check the weight change for the node
                if node not in self.vector and node != self.name:
                    if self.is_outgoing_neighbor(node):
                        weight = int(self.get_outgoing_neighbor_weight(node))
                    else:
                        weight = int(self.get_outgoing_neighbor_weight(msg["source"])) + int(msg["vector"][node])
                    self.vector[node] = weight
                    update_distance = True
        #if node is in DV but is not local node, then check the weight change for the node
                elif node in self.vector and node != self.name:
                    self_to_source = int(self.get_outgoing_neighbor_weight(msg["source"]))
                    source_to_node = int(msg["vector"][node])
                    added_distance = self_to_source + source_to_node
        # Negative cycle stop at -99
                    if self_to_source <= -99 or source_to_node <= -99 and self.vector[node] != -99:
                        self.vector[node] = -99
                        update_distance = True
                    else:
                        if added_distance < self.vector[node] and added_distance > -99:
                            self.vector[node] = added_distance
                            update_distance = True
                        elif added_distance <= -99 and self.vector[node] != -99:
                            self.vector[node] = -99
                            update_distance = True
        
        # Empty queue
        self.messages = []

        # TODO 2. Send neighbors updated distances               
        if update_distance is True:
            self.send_to_incoming_links()

    def log_distances(self):
        ''' This function is called immedately after process_BF each round.  It 
        prints distances to the console and the log file in the following format (no whitespace either end):
        
        A:A0,B1,C2
        
        Where:
        A is the node currently doing the logging (self),
        B and C are neighbors, with vector weights 1 and 2 respectively
        NOTE: A0 shows that the distance to self is 0 '''
        
        # TODO: Use the provided helper function add_entry() to accomplish this task (see helpers.py).
        # An example call that which prints the format example text above (hardcoded) is provided.        
        #add_entry("A", "A0,B1,C2")        
        seperator = ','
        texts = []
        for key in self.vector:
            texts.append(key + str(self.vector[key]))   
        texts.sort()
        add_entry(self.name, seperator.join(texts)) 




