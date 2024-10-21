import React, { useEffect, useState } from 'react'
import { Link } from 'react-router-dom'
import { ContactSercvices  } from '../../../Services/ContactServices'
import Spinner from '../../Assets/Spinner'

const ContactList = () => {

  const[query,setQuery]=useState({
    text:""
  })
  const[state,setState] =useState({
    loading:true,
    contacts:[],
    filterdContact:[],
    errorMassage:''
  })

  useEffect(()=>{
    let promise=new Promise((res,rej)=>{
      setState({...state,loading:true,contacts:[]});
      let response=ContactSercvices.getAllContacts()
      res(response)
    })

    promise.then((res1)=>{
      setState({...state,loading:false,contacts:res1.data,filterdContact:res1.data});
    }).catch(()=>{
      setState({...state,loading:false,errorMassage:alert('data not found')});
    })
  },[])
// delete logic
  let clickDelete=(contactId)=>{
    let promise= new Promise((res,rej)=>{
      let deleteContact=ContactSercvices.deleteContact(contactId)
      res(deleteContact)
    })

    promise.then((res1)=>{
      if (res1) {
        let promise=new Promise((res,rej)=>{
          setState({...state,loading:true,contacts:[]});
          let response=ContactSercvices.getAllContacts()
          res(response)
        })
    
        promise.then((res1)=>{
          setState({...state,loading:false,contacts:res1.data,filterdContact:res1.data});
        }).catch(()=>{
          setState({...state,loading:false,errorMassage:alert('data not found')});
        })
      }
    })
  }
  // searchQuery
  let searchContact=(event)=>{
    setQuery({...query,text:event.target.value})

    let theContacts=state.contacts.filter((contact)=>{
      return contact.name.toLowerCase().includes(event.target.value.toLowerCase());
    })
    setState({...state,filterdContact:theContacts})
  }
  
  let {loading,contacts,errorMassage,filterdContact}=state
  
  return (
    <>
      <section className='contact-search'>
         <div className="container p-3">
          <div className="grid">
            <div className="row">
              <div className="col">
                <p className='h3'>Contact Manager <Link to={'/Contacts/add'} className='btn btn-primary ms-2'><i className='fa fa-plus-circle me-2'/>Add</Link></p>
                <p className='fst-italic'>Lorem ipsum dolor sit amet consectetur adipisicing elit. Atque possimus accusantium illo nemo error. Harum vel aliquam rem. Neque harum modi quas consectetur et accusantium laborum, blanditiis nostrum in sapiente!</p>
              </div>
            </div>
          </div>
          <div className="row">
          <div className="col-md-6">
            <form action="" className='row'>
              
              <div className="col">
                <div className="mb-2">
                  <input type="text" name='text' value={query.text} placeholder=' Search name' onChange={searchContact} className=' form-control'  />
                </div>
              </div>
              <div className="col">
                <div className="mb-2">
                 {/* <input type="submit" value="Search" className='btn btn-outline-primary ' /> */}
                </div>
              </div>
            </form>
          </div>
         </div>
         </div>
       </section>
       {
        loading?<Spinner/>:<React.Fragment>
          <section className='contact-list'>
        <div className="container">
          <div className="row">
            {
              filterdContact.length>0 &&filterdContact.map((contact)=>{
                let{photo,name,mobile,email,id}=contact
                return(
                  <div className="col-md-6 mb-4" key={id}>
              <div className="card d-flex justify-content-around" >
                <div className="card-body">
                  <div className="row align-items-center">
                    <div className="col-md-4">
                      <img src={photo} alt="" className='contact-img' />
                    </div>
                     <div className="col-md-7">
                        <ul className="list-group">
                          <li className="list-group-item list-group-item-action">Name:{name}</li>
                          <li className="list-group-item list-group-item-action">Mobile:{mobile}</li>
                          <li className="list-group-item list-group-item-action ">Email:{email}</li>
                        </ul>
                     </div>
                     <div className="col-md-1 d-flex flex-column align-items-center">

                      <Link to={`/Contacts/view/${contact.id}`} className='btn btn-warning my-1'><i className='fa fa-eye'/></Link>
                      <Link to={`/Contacts/edit/${contact.id}`} className='btn btn-primary my-1'><i className='fa fa-pen'/></Link>
                      <button className='btn btn-danger my-1' onClick={()=>{clickDelete(contact.id)}}><i className='fa fa-trash'/></button>
                     </div>
                  </div>
                </div>
              </div>
            </div>
                )
              })
            }
           
          </div>
        </div>
       </section>

        </React.Fragment>

       }
       
    </>
  )
}

export default ContactList

