import {useEffect} from "react";

export const Test = (props: any) =>{
    useEffect(() => {
        console.log({ ...props})
    }, []);

    return <div>
        test
    </div>
}
