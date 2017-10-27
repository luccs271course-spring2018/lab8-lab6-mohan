<h1> Why does FixedArrayQueue require an explicit constructor?</h1> 
   Because, unlike the linkedstacked structure, it is not implemented within the interface. All methods pertinent 
   to fixedarrayqueue do not come within the structure.
<h1> What happens when you offer an item to a full FixedArrayQueue?</h1> 
   The system will print out, "queue full 'name' unable to join". This would usually return false. 
<h1> What happens when you poll an empty FixedArrayQueue?</h1> 
   It will return "null". 
<h1> What is the time and (extra) space complexity of each of the FixedArrayQueue methods?</h1> 
offer is O(1), a reference is always mantained; peek and poll both operate in O(n)(no reference maintained); isempty and size are both O(1).