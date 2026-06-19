fetch("https://jsonplaceholder.typicode.com/posts/3",
    {
        method:"PUT",
        headers:{
            'Content-Type':'application/json'
        },
        body:JSON.stringify({
            title:'Title',
            body:"Body",
            userId:5
        })
    })
.then(response=>response.json())
.then(data=>{console.log(data)})

